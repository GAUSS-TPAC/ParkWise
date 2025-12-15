import requests
import json
import time
from requests.adapters import HTTPAdapter
from urllib3.util.retry import Retry


# Configuration des timeouts et retries
def create_session():
    """Create a requests session with timeout and retry configuration"""
    session = requests.Session()

    # Configure retry strategy
    retry_strategy = Retry(
        total=3,
        backoff_factor=1,
        status_forcelist=[429, 500, 502, 503, 504],
    )

    adapter = HTTPAdapter(max_retries=retry_strategy)
    session.mount("https://", adapter)
    session.mount("http://", adapter)

    return session


# Definition des parametres
subscription_key_user_create = 'b5c09124354140a1b2ac4482a233e800'
subscription_key_trans_create = '428ba4c1b7974a9085108aa811b2a414'
unique_ref = '6a104bdd-4c30-4733-a578-fbf41e58e49b'

# Create session with retry logic
session = create_session()

# Set timeout (connect timeout, read timeout) in seconds
TIMEOUT = (10, 30)

print("Testing connection to MTN MoMo API...")

try:
    # Creation de User-api
    url = 'https://sandbox.momodeveloper.mtn.com/v1_0/apiuser'
    body = {"providerCallbackHost": "string"}
    headers = {
        'X-Reference-Id': unique_ref,
        'Content-Type': 'application/json',
        'Ocp-Apim-subscription-key': subscription_key_user_create
    }

    print(f"Attempting to create API user...")
    r = session.post(url, data=json.dumps(body), headers=headers, timeout=TIMEOUT)
    print(f"Response status: {r.status_code}")

    if r.status_code == 201:
        print("✓ Creation de user API effectif")

        # Creation de l'API key
        url = f'https://sandbox.momodeveloper.mtn.com/v1_0/apiuser/{unique_ref}/apikey'
        body = {"providerCallbackHost": "string"}
        headers = {'Ocp-Apim-subscription-key': subscription_key_user_create}

        print("Creating API key...")
        r = session.post(url, data=json.dumps(body), headers=headers, timeout=TIMEOUT)
        print(f"Response status: {r.status_code}")

        if r.status_code == 201:
            user_key_tojson = r.json()
            apikey = user_key_tojson['apikey']
            print(f'✓ API key created: {apikey}')

            # Creation du token d'access
            url = "https://sandbox.momodeveloper.mtn.com/collection/token/"
            headers = {'Ocp-Apim-subscription-key': subscription_key_trans_create}

            print("Requesting access token...")
            r = session.post(url, headers=headers, auth=(unique_ref, apikey), timeout=TIMEOUT)
            print(f"Response status: {r.status_code}")

            if r.status_code == 200:
                json_content = r.json()
                access_token = json_content['access_token']
                token_type = json_content['token_type']
                expires_in = json_content['expires_in']

                print(f'✓ Access token obtained')
                print(f'  Token type: {token_type}')
                print(f'  Expires in: {expires_in} seconds')

                # Demande de payment
                montant = 2500
                devise = 'EUR'
                transaction_id = '1243412344'
                payor_phone = '237672171924'
                payor_message = 'paiement numero 5456'
                payee_message = 'reglement OrderID: 5456'

                body = {
                    'amount': montant,
                    'currency': devise,
                    'externalId': transaction_id,
                    'payer': {
                        'partyIdType': 'MSISDN',
                        'partyId': payor_phone
                    },
                    'payerMessage': payor_message,
                    'payeeNote': payee_message
                }

                headers = {
                    'Authorization': 'Bearer ' + access_token,
                    'X-Reference-Id': unique_ref,
                    'X-Target-Environment': 'sandbox',
                    'Content-Type': 'application/json',
                    'Ocp-Apim-subscription-key': subscription_key_trans_create
                }

                url = "https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay"

                print("Requesting payment...")
                r = session.post(url, data=json.dumps(body), headers=headers, timeout=TIMEOUT)
                print(f"Response status: {r.status_code}")

                if r.status_code == 202:
                    print("✓ Transaction accepted!")
                    print(f"Transaction ID: {transaction_id}")
                else:
                    print(f"✗ Payment request failed: {r.status_code}")
                    print(f"Response: {r.text}")
            else:
                print(f"✗ Token request failed: {r.status_code}")
                print(f"Response: {r.text}")
        else:
            print(f"✗ API key creation failed: {r.status_code}")
            print(f"Response: {r.text}")
    elif r.status_code == 409:
        print("⚠ API user already exists (409 Conflict)")
        print("This is normal if you've run this script before with the same unique_ref")
        print("You can continue with the existing user or generate a new unique_ref")
    else:
        print(f"✗ User creation failed: {r.status_code}")
        print(f"Response: {r.text}")

except requests.exceptions.ConnectTimeout as e:
    print("\n✗ CONNECTION TIMEOUT ERROR")
    print("Cannot connect to MTN MoMo API. Possible reasons:")
    print("1. Network/firewall blocking the connection")
    print("2. Proxy configuration needed")
    print("3. MTN MoMo sandbox is down")
    print("4. DNS resolution issues")
    print(f"\nError details: {e}")

except requests.exceptions.ConnectionError as e:
    print("\n✗ CONNECTION ERROR")
    print("Network connection failed.")
    print(f"Error details: {e}")

except requests.exceptions.RequestException as e:
    print("\n✗ REQUEST ERROR")
    print(f"Error details: {e}")

except Exception as e:
    print("\n✗ UNEXPECTED ERROR")
    print(f"Error: {e}")

finally:
    session.close()
    print("\nScript completed.")
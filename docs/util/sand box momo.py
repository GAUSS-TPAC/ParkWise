import requests
import json
import uuid
import random

# definition des parametres:
subscription_key_user_create = 'b5c09124354140a1b2ac4482a233e800'
subscription_key_trans_create = '428ba4c1b7974a9085108aa811b2a414'
unique_ref = '6a104bdd-4c30-4733-a578-fbf41e58e49b'  # à remplacer par la reference utilisateur pour le "Go live"
url = 'https://sandbox.momodeveloper.mtn.com/v1_0/apiuser'
body = {"providerCallbackHost": "string"}
headers = {'X-Reference-Id': unique_ref, 'Content-Type': 'application/json',
           'Ocp-Apim-subscription-key': subscription_key_user_create}

# creation de User-api si success on vera 201
r = requests.post(url, data=json.dumps(body), headers=headers)
print(r)

# si la  creation aboutit avec success, alors on continue pour la creation du api key.
if r.status_code == 201:
    print("Creation de user API effectif")

    url = f'https:// sandbox.momodeveloper.mtn.com/v1_0/apiuser/{unique_ref}/apikey'
    body = {"providerCallbackHost": "string"}
    headers = {'Ocp-Apim-subscription-key': subscription_key_user_create}

    r = requests.post(url, data=json.dumps(body), headers=headers)
    print(r)
    print(r.content)
    user_key_tojson = r.json()
    apikey = user_key_tojson['apikey']
    print('le API key a ete cree avec success !!: ', apikey)
    # print(type(apikey))

    # Maintenant nous allons creer notre Authorization avec chiffrement en base64

    # maintenant
    url = "https:// sandbox.momodeveloper.mtn.com/collection/token/"
    headers = {'Ocp-Apim-subscription-key': subscription_key_trans_create}
    r.requests.post(url, headers=headers, auth=(unique_ref, apikey))
    # print(r)
    # print(r.content)

    if r.status_code == 200:
        json_content = r.json()
        access_token = json_content['access_token']
        token_type = json_content['token_type']
        expires_in = json_content['expires_in']
        print('access_token:', access_token)
        print('token_type:', token_type)
        print('expires_in:', expires_in)

    # Maintenant nous pouvons commencer toutes sorte de transaction : Collection, distribushment,

    # pour effectuer notre premiere demande de payment: on va commencer par definir les valeurs,
    montant = 2500
    devise = 'EUR'
    id = '1243412344'
    payor_phone = '237672171924'
    payor_message = 'paiement numero 5456'
    payee_message = 'reglement OrderID: 5456'

    body = {
        'amount': montant,
        'currency': devise,
        'externalId': id,
        'payer': {
            'partyIdType': 'MISSION',
            'partyId': payor_phone
        },
        'payerMessage': payor_message,
        'payeeNote': payee_message
    }

    headers = {
        # Request Headers
        'Authorization': 'Bearer' + access_token,
        'X-Reference-Id': unique_ref,
        'X-Target_enviroment': 'sandbox',
        'Content-Type': 'application/json',
        'Ocp-Apim-subscription-key': subscription_key_user_create
    }

    url = "https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay"

    r.requests.post(url, data=json.dumps(body).encode("ascii"), headers=headers)
    print(r)
    print("la transaction a ete deroulé san s aucun probleme !")
    print("Good Bye")
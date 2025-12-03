import requests
import json
import uuid
import random

# definition des parametres:
subscription _key_user_create = ''
subscription_key_trans_create =''
unique_ref = str(uuid.uuid4()) #à remplacer par la reference utilisateur pour le "Go live"
url = 'https://sandbox.momodeveloper.mtn.com/v1_0/apiuser'
body = {"providerCallbackHost":"string"}
header = {'X-Reference':unique_ref, 'Content-Type':'application/json', 'Ocp-Apim-subs....'}
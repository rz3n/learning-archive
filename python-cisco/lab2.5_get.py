import json
import requests

requests.packages.urllib3.disable_warnings()

api_url = "https://10.10.20.48/restconf/data/ietf-interfaces:interfaces"

headers = {
  "Accept": "application/yang-data+json",
  "Content-Type": "application/yang-data+json"
}

basicauth = ("developer", "C1sco12345")

response = requests.get(api_url, auth=basicauth, headers=headers, verify=False)

response_json = response.json()
print(json.dumps(response_json, indent=2))
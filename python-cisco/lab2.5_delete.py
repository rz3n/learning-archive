import json
import requests

requests.packages.urllib3.disable_warnings()

api_url = "https://10.10.20.48/restconf/data/ietf-interfaces:interfaces/interface=Loopback99"

headers = {
  "Accept": "application/yang-data+json",
  "Content-Type": "application/yang-data+json"
}

basicauth = ("developer", "C1sco12345")

response = requests.delete(
  api_url,
  headers=headers,
  auth=basicauth,
  verify=False)

if (response.status_code >= 200 and response.status_code <= 299):
  print("Status: {}".format(response.status_code))
else:
  print("Error code: {}".format(response.status_code))
  print("Error text: {}".format(response.json()))

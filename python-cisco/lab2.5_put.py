import json
import requests

requests.packages.urllib3.disable_warnings()

api_url = "https://10.10.20.48/restconf/data/ietf-interfaces:interfaces/interface=Loopback99"

headers = {
  "Accept": "application/yang-data+json",
  "Content-Type": "application/yang-data+json"
}

basicauth = ("developer", "C1sco12345")

yangConfig = {
  "ietf-interfaces:interface": {
    "name": "Loopback99",
    "description": "Configured by RESTCONF",
    "type": "iana-if-type:softwareLoopback",
    "enabled": True,
    "ietf-ip:ipv4": {
      "address": [
        {
          "ip": "99.99.99.99",
          "netmask": "255.255.255.0"
        }
      ]
    },
    "ietf-ip:ipv6": {}
  }
}


response = requests.put(
  api_url,
  data=json.dumps(yangConfig),
  headers=headers,
  auth=basicauth,
  verify=False)

if (response.status_code >= 200 and response.status_code <= 299):
  print("Status: {}".format(response.status_code))
else:
  print("Error code: {}".format(response.status_code))
  print("Error text: {}".format(response.json()))


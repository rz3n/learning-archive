from ncclient import manager
import xml.dom.minidom
import xmltodict

m = manager.connect(
  host = "10.10.20.48",
  port = 830,
  username = "developer",
  password = "C1sco12345",
  hostkey_verify = False,
)

netfilter_stats = '''
<filter xmlns="urn:ietf:params:xml:ns:netconf:base:1.0">
  <interfaces-stats xmlns="urn:ietf:params:xml:ns:yang:ietf-interfaces" />
</filter>
'''

netfilter_interfaces = '''
<filter xmlns="urn:ietf:params:xml:ns:netconf:base:1.0">
  <interfaces xmlns="urn:ietf:params:xml:ns:yang:ietf-interfaces"/>
</filter>
'''

#netconf_reply = m.get(netfilter)
netconf_reply = m.get(filter=netfilter_stats)
netconf_reply_dict = xmltodict.parse(netconf_reply.xml)

if len(netconf_reply_dict) > 1:
  for interface in netconf_reply_dict["rpc-reply"]["data"]["interfaces-state"]["interface"]:
    print("Name: {} MAC: {} Input: {} Output: {}".format(
      interface["name"],
      interface["phys-address"],
      interface["statistics"]["in-octets"],
      interface["statistics"]["out-octets"])
    )
else:
  print(">> No statistics found. Printing interfaces instead:")
  netconf_reply = m.get(filter=netfilter_interfaces)
  netconf_reply_dict = xmltodict.parse(netconf_reply.xml)

  for interface in netconf_reply_dict["rpc-reply"]["data"]["interfaces"]["interface"]:
    print("Name: {} Description: {} Enabled: {}".format(
      interface["name"],
      interface["description"],
      interface["enabled"])
    )

m.close_session()
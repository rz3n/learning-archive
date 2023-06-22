from ncclient import manager
import xml.dom.minidom

m = manager.connect(
  host = "10.10.20.48",
  port = 830,
  username = "developer",
  password = "C1sco12345",
  hostkey_verify = False,
)

netconf_data = '''
<config xmlns="urn:ietf:params:xml:ns:netconf:base:1.0">
  <native xmlns="http://cisco.com/ns/yang/Cisco-IOS-XE-native">
    <hostname>LAB-2.8</hostname>
    <interface>
      <Loopback>
        <name>100</name>
        <description>Test1</description>
        <ip>
          <address>
            <primary>
              <address>100.100.100.100</address>
              <mask>255.255.255.0</mask>
            </primary>
          </address>
        </ip>
      </Loopback>
    </interface>
  </native>
</config>
'''

reply = m.edit_config(
  target='running',
  config=netconf_data, # returning "<bad-element>config" error
  default_operation='merge'
)

print(xml.dom.minidom.parseString(reply.xml).toprettyxml())


m.close_session()
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

netconf_filter = '''
<filter xmlns="urn:ietf:params:xml:ns:netconf:base:1.0">
  <interfaces xmlns="urn:ietf:params:xml:ns:yang:ietf-interfaces">
    <interface>
      <name>GigabitEthernet1</name>
    </interface>
  </interfaces>
</filter>
'''


running_config = m.get_config('running', netconf_filter)


m.close_session()
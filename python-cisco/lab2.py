import netmiko

sshCli = netmiko.ConnectHandler(
    device_type='cisco_ios',
    host='10.10.20.48',
    port=22,
    username='developer',
    password='C1sco12345'
)

# create a new loopback interface
configCommands = [
  'int loop 1',
  'ip addr 2.2.2.2 255.255.255.0',
  'description created by Python'
]
response = sshCli.send_config_set(configCommands)
#print("Response:\n{}\n".format(response))


# second loopback interface
configCommands = [
  'int loop 2',
  'ip addr 2.2.2.2 255.255.255.0',
  'description created by Python 2'
]
response = sshCli.send_config_set(configCommands)
print("Response:\n{}\n".format(response))



# create a dictionary of interfaces and their IP addresses
response = sshCli.send_command('show ip int brief')
#print("Response:\n{}\n".format(response))

intDict = {}
for line in response.splitlines():
  if 'Interface' in line:
    continue
  fields = line.split()
  if fields[1] != 'unassigned':
    intDict[fields[0]] = fields[1]

print("Interfaces with IP address:\n{}\n".format(intDict))
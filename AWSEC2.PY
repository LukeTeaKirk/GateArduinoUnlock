import socket
import sys
import serial
import time
# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Bind the socket to the port
server_address = ('0.0.0.0', 63890)
sock.bind(server_address)
# Listen for incoming connection
sock.listen(1)
while True:
    connection, client_address = sock.accept()
    try:
print('connection from', client_address)
sock2 = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_address2 = ('192.168.192.144', 63890)
sock2.connect(server_address2)

    finally:
connection.close()

import socket
import sys
import serial

ser = serial.Serial()
ser.baudrate = 9600
ser.port = "COM3"
# Create a TCP/IP socket
sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# Bind the socket to the port
server_address = ('0.0.0.0', 63890)
sock.bind(server_address)
# Listen for incoming connections
sock.listen(1)
ser.open()

while True:
    # Wait for a connection
    connection, client_address = sock.accept()
    try:
          print('connection from', client_address)
          ser.write(b'1776')

    finally:
        connection.close()

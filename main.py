import boto3
import socket
import sys
import serial
import time

import sock as sock

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
s3 = boto3.resource('s3', region_name='us-east-2')
obj = s3.Object("dooropener", "example2.txt")
body = obj.get()
body = body['LastModified']
print(body)

def looper():
    while True:
        global body
        body2 = obj.get()
        body2 = body2['LastModified']
        print(body2)
        time.sleep(2)
        if body == body2:
            print("same")
        else:
            print("requested bell")
            body = body2
            ser.write(b'1776')
def loopy():
    try:
        looper()
    except:
        loopy()
loopy()
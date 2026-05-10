import os

name = input("Enter filename: ")

# SECURITY ISSUE: Command Injection
os.system("cat " + name)

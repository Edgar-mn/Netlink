import random
import string
import re
import paramiko

# Function to generate a random reference
def generate_random_reference(length=10):
    return ''.join(random.choices(string.ascii_uppercase + string.digits, k=length))

# Function to change Field 20 in the SWIFT message
def change_field_20(swift_message, new_reference):
    # Replace the value of Field 20 with the new reference
    updated_message = re.sub(r'(:20:)(\w+)', r'\1' + new_reference, swift_message)
    return updated_message

# Read SWIFT message from the specified file
def read_message_from_file(file_path):
    with open(file_path, 'r') as file:
        return file.read()

# Write modified message to a file
def write_message_to_file(file_path, message):
    with open(file_path, 'w') as file:
        file.write(message)

# Function to send a file to SFTP server
def send_to_sftp(local_file_path, remote_file_path, hostname, port, username, password):
    try:
        transport = paramiko.Transport((hostname, port))
        transport.connect(username=username, password=password)
        sftp = paramiko.SFTPClient.from_transport(transport)
        sftp.put(local_file_path, remote_file_path)
        sftp.close()
        transport.close()
        print("File successfully sent to SFTP server")
    except Exception as e:
        print(f"Failed to send file to SFTP server: {e}")

# Example usage with the specified file path
input_file_path = "Z:\\2_Qualifications\\100_Message Samples ITB\\SU3\\MT\\SU3_MT_ACK.txt"
output_file_path = "C:\\Users\\user\\Downloads\\MT_ACK_modified.txt"

# Generate a new random reference
new_reference = generate_random_reference()
print(f"New Reference: {new_reference}")

# Read the original SWIFT message
swift_message = read_message_from_file(input_file_path)
print("Original Message:\n", swift_message)  # Debug

# Change Field 20 to the new random reference
modified_message = change_field_20(swift_message, new_reference)
print("Modified Message:\n", modified_message)  # Debug

# Write the modified message to the output file
write_message_to_file(output_file_path, modified_message)

# Send the modified file to the SFTP server
hostname = "cloudsftp.netlink-testlabs.com"  # Replace with your SFTP server hostname
port = 22  # Default SFTP port
username = "swiftuser3"  # Replace with your SFTP server username
password = "Password1!"  # Replace with your SFTP server password
remote_file_path = "/MT/toswift/MTLAU_ACK_modified.txt"  # Replace with the desired path on the SFTP server

send_to_sftp(output_file_path, remote_file_path, hostname, port, username, password)

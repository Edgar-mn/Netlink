from ftplib import FTP_TLS
import paramiko
import os
import random
import string
import codecs

sftp_server = 'd1z.netlinksc.com'
sftp_port = 8221
sftp_user = 'swiftuser4'
sftp_password = 'Password1!'
local_path = r"C:\Users\user\Downloads\SU4.xml"  # Ensure this path is correct



# Function to generate random string
def generate_random_string(length):
    letters = string.ascii_letters + string.digits
    return ''.join(random.choice(letters) for i in range(length))
random_string = generate_random_string(10)
# Original text
MX = f'''<?xml version="1.0" encoding="UTF-8"?>
<Saa:DataPDU xmlns:Saa="urn:swift:saa:xsd:saa.2.0" xmlns:Sw="urn:swift:snl:ns.Sw" xmlns:SwInt="urn:swift:snl:ns.SwInt" xmlns:SwGbl="urn:swift:snl:ns.SwGbl" xmlns:SwSec="urn:swift:snl:ns.SwSec">
	<Saa:Revision>2.0.2</Saa:Revision>
	<Saa:Header>
		<Saa:Message>
			<Saa:SenderReference>{random_string}</Saa:SenderReference>
			<Saa:MessageIdentifier>pacs.008.001.08</Saa:MessageIdentifier>
			<Saa:Format>MX</Saa:Format>
			<Saa:SubFormat>Input</Saa:SubFormat>
			<Saa:Sender>
				<Saa:DN>cn=torch,o=ptsqgbbb,o=swift</Saa:DN>
				<Saa:FullName>
					<Saa:X1>PTSQGBBBXXX</Saa:X1>
				</Saa:FullName>
			</Saa:Sender>
			<Saa:Receiver>
				<Saa:DN>cn=swiftuser4,ou=tor,o=ptsqgbbb,o=swift</Saa:DN>
				<Saa:FullName>
					<Saa:X1>PTSQGBBBXXX</Saa:X1>
					<Saa:X2>saa</Saa:X2>
				</Saa:FullName>
			</Saa:Receiver>
			<Saa:InterfaceInfo>
				<Saa:UserReference>MX2510</Saa:UserReference>
				<Saa:MessageCreator>ApplicationInterface</Saa:MessageCreator>
				<Saa:MessageContext>Original</Saa:MessageContext>
				<Saa:MessageNature>Financial</Saa:MessageNature>
			</Saa:InterfaceInfo>
			<Saa:NetworkInfo>
				<Saa:Priority>Normal</Saa:Priority>
				<Saa:IsPossibleDuplicate>true</Saa:IsPossibleDuplicate>
				<Saa:Service>swift.finplus!xpf</Saa:Service>
				<Saa:Network>Application</Saa:Network>
				<Saa:SessionNr>0000</Saa:SessionNr>
				<Saa:SeqNr>000000</Saa:SeqNr>
				<Saa:SWIFTNetNetworkInfo>
					<Saa:RequestType>pacs.008.001.08</Saa:RequestType>
					<Saa:Reference>07894e71-9ecd-42b8-b088-58d55d1df44f</Saa:Reference>
				</Saa:SWIFTNetNetworkInfo>
			</Saa:NetworkInfo>
			<Saa:SecurityInfo>
				<Saa:IsSigningRequested>true</Saa:IsSigningRequested>
				<Saa:SWIFTNetSecurityInfo>
					<Saa:IsNRRequested>false</Saa:IsNRRequested>
				</Saa:SWIFTNetSecurityInfo>
			</Saa:SecurityInfo>
		</Saa:Message>
	</Saa:Header>
	<Saa:Body>
		<AppHdr xmlns="urn:iso:std:iso:20022:tech:xsd:head.001.001.02" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<Fr>
				<FIId>
					<FinInstnId>
						<BICFI>CHASGB2LXXX</BICFI>
					</FinInstnId>
				</FIId>
			</Fr>
			<To>
				<FIId>
					<FinInstnId>
						<BICFI>CHASUS33XXX</BICFI>
					</FinInstnId>
				</FIId>
			</To>
			<BizMsgIdr>ELC/MBW/19/24072</BizMsgIdr>
			<MsgDefIdr>pacs.008.001.08</MsgDefIdr>
			<BizSvc>swift.cbprplus.02</BizSvc>
			<CreDt>2023-07-12T10:43:31.576+02:00</CreDt>
			<PssblDplct>false</PssblDplct>
			<Prty>NORM</Prty>
		</AppHdr>
		<Document xmlns="urn:iso:std:iso:20022:tech:xsd:pacs.008.001.08" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
			<FIToFICstmrCdtTrf>
				<GrpHdr>
					<MsgId>xirnipacs008id02082021053021</MsgId>
					<CreDtTm>2023-07-12T10:43:31.576+02:00</CreDtTm>
					<NbOfTxs>1</NbOfTxs>
					<SttlmInf>
						<SttlmMtd>INDA</SttlmMtd>
						<SttlmAcct>
							<Id>
								<Othr>
									<Id>BO-NYNY-BOST NY-CV-USD-2</Id>
								</Othr>
							</Id>
						</SttlmAcct>
					</SttlmInf>
				</GrpHdr>
				<CdtTrfTxInf>
					<PmtId>
						<InstrId>ELC/MBW/19/0007</InstrId>
						<EndToEndId>ELC/MBW/19/0007</EndToEndId>
						<UETR>0902c16a-b699-4e69-a40d-1536d11ed403</UETR>
					</PmtId>
					<IntrBkSttlmAmt Ccy="USD">72817328173</IntrBkSttlmAmt>
					<IntrBkSttlmDt>2019-09-24</IntrBkSttlmDt>
					<InstdAmt Ccy="GBP">50000000000</InstdAmt>
					<XchgRate>1.5619</XchgRate>
					<ChrgBr>CRED</ChrgBr>
					<ChrgsInf>
						<Amt Ccy="GBP">337903790</Amt>
						<Agt>
							<FinInstnId>
								<Nm>NOTPROVIDED</Nm>
								<PstlAdr>
									<AdrLine>NOTPROVIDED</AdrLine>
								</PstlAdr>
							</FinInstnId>
						</Agt>
					</ChrgsInf>
					<ChrgsInf>
						<Amt Ccy="USD">0</Amt>
						<Agt>
							<FinInstnId>
								<Nm>NOTPROVIDED</Nm>
								<PstlAdr>
									<AdrLine>NOTPROVIDED</AdrLine>
								</PstlAdr>
							</FinInstnId>
						</Agt>
					</ChrgsInf>
					<InstgAgt>
						<FinInstnId>
							<BICFI>CHASGB2LXXX</BICFI>
						</FinInstnId>
					</InstgAgt>
					<InstdAgt>
						<FinInstnId>
							<BICFI>CHASUS33XXX</BICFI>
						</FinInstnId>
					</InstdAgt>
					<Dbtr>
						<Id>
							<OrgId>
								<AnyBIC>CHASGB2LXXX</AnyBIC>
							</OrgId>
						</Id>
					</Dbtr>
					<DbtrAgt>
						<FinInstnId>
							<BICFI>CHASGB2LXXX</BICFI>
						</FinInstnId>
					</DbtrAgt>
					<CdtrAgt>
						<FinInstnId>
							<BICFI>CHASUS33XXX</BICFI>
						</FinInstnId>
					</CdtrAgt>
					<Cdtr>
						<Nm>BELLVIEW GARDENS COMPLEX</Nm>
						<PstlAdr>
							<AdrLine>4TH AVENUE</AdrLine>
							<AdrLine>NEW YORK, NY2078</AdrLine>
						</PstlAdr>
					</Cdtr>
					<InstrForNxtAgt>
						<InstrInf>/ACC/SETTLE THROUGH VOSTRO ACCOUNT</InstrInf>
					</InstrForNxtAgt>
					<RmtInf>
						<Ustrd>/RFB/DOCSPRESENT01</Ustrd>
					</RmtInf>
				</CdtTrfTxInf>
			</FIToFICstmrCdtTrf>
		</Document>
	</Saa:Body>
</Saa:DataPDU>
'''
print(MX)

file_path = r"C:\Users\user\Downloads\SU4.xml"  # Ensure this path is correct

# Normalize line endings
#MTN = MT.replace('\r\n', '\n').replace('\r', '\n')

# Write the text to the file with UTF-8 BOM encoding


with codecs.open(file_path, 'w', encoding='utf-8') as file:  # 'w' mode to write text
    file.write(MX)
# Replace part of the text

remote_path = '/swiftuser4/MX/toswift/SU4.xml'
try:
    paramiko.util.log_to_file('paramiko.log')  # Enable logging

    if not os.path.exists(local_path):
        print(f"Local file does not exist: {local_path}")
    else:
        print(f"Local file found: {local_path}")

    transport = paramiko.Transport((sftp_server, sftp_port))
    transport.connect(username=sftp_user, password=sftp_password)

    sftp = paramiko.SFTPClient.from_transport(transport)

    print(f"Uploading {local_path} to {remote_path} on server")

    sftp.put(local_path, remote_path)
    
    print('File uploaded successfully to the SFTP server.')
    sftp.close()
    transport.close()
except Exception as e:
    print(f'Error: {e}')
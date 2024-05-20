# ZMessages
A simple library for displaying messages in the console.


### How to use API? (Examples)
<details>
<summary> 
Send Simple Mail
</summary>

Methods: POST

Path:  /zmessages/api/mail

Request Body:
```json
{
  "from_address" : "madan@xindus.net",
  "to_address_list" : ["madan@xindus.net"],
  "cc_address_list" : ["madan@xindus.net"],
  "bcc_address_list" : ["madan@xindus.net"],
  "subject" : "Mail Test",
  "msg_body" : "Mail Sent from ZMessages API"
}
```
Success Response: 200 OK
```json
{
  "status": "ok",
  "code": "200 OK",
  "description": "Operation Completed Successfully!",
  "data": [
    "Mail Sent Successfully..."
  ]
}
```
</details>



<details>
<summary> 
Send MIME Mail (Attachments)
</summary>

Methods: POST

Path:  /zmessages/api/mail/base64

Request Body:
```json
{
  "from_address": "madan@xindus.net",
  "to_address_list": [
    "madan@xindus.net"
  ],
  "cc_address_list": [
    "madan.kn@gmail.com"
  ],
  "bcc_address_list": [
    "madan.kn@gmail.com"
  ],
  "subject": "Use ZMessages API - For Sending Emails - Test 2",
  "msg_body": "Mail Sent from ZMessages API -- https://github.com/jayameen/zmessages ",
  "attachment_list": [
    {
      "attachment_name": "postman.png",
      "attachment_base64": "iVBORw0KGgoAAAANSUhEUg=="
    }
  ]
}
```
Success Response: 200 OK
```json
{
  "status": "ok",
  "code": "200 OK",
  "description": "Operation Completed Successfully!",
  "data": [
    "Mail Sent Successfully..."
  ]
}
```
</details>

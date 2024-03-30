# Manual

## Description
This is a Session Description Protocol (SDP) Encryptor app, which encrypts user's input text into a ciphertext.

## Inputs
There are 3 inputs, a plaintext, an Alpha Key and a Beta Key.

| Input field | Value |
|---|---|
|`Plaintext`|a random combination of alphabet(s), number(s) and/or special character(s), with or without empty space(s). **At least one letter** must be in the plaintext.|
|`Alpha Key`|an integer coprime to 26 between 0 and 26, i.e. 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, or 25.|
|`Beta Key`| an integer >=1 and <26|

## How to run
1. Input a random text in the `Plaintext` field, **at least one letter** in the text.
2. Input an integer which belongs to Alpha Keys in the field `Alpha Key`
3. Input an integer which belongs to Beta Keys in the field `Beta Key`
4. Click `ENCIPHER` button

The input text will be converted to ciphertext and shown below the `Ciphertext:` on the screen.

## Error message
|Error message|Meaning|
|---|---|
|Invalid Plaintext| input text is empty or letterless|
|Invalid Alpha Key| input integer is blank or not coprime to 26|
|Invalid Beta Key| input integer is blank out-of-range of 1-25|


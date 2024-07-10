```openssl req -newkey rsa:2048 -x509 -keyout key.pem -out cert.pem -days 365```

password: spring

- key.pem (the private key)
- cert.pem (a public certificate)

```openssl pkcs12 -export -in cert.pem -inkey key.pem -out certificate.p12 -name "certificate"```
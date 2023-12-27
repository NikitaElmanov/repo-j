___
**NOTE**

All commands from manual is needed to execute from root of project
___

# Http server

## Create keystore

```bash
keytool -v -genkeypair -dname "CN=Russia,OU=Ryazan,O=DEv,C=RU" -keystore http-server/src/main/resources/ssl/server-keystore.p12 -storepass qwe123 -keypass qwe123 -keyalg RSA -keysize 2048 -alias server -validity 99999 -deststoretype pkcs12 -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth -ext SubjectAlternativeName:c=DNS:localhost,DNS:raspberrypi.local,IP:127.0.0.1
```

## Export certificate

```bash
keytool -v -exportcert -file http-server/src/main/resources/ssl/server.cer -alias server -keystore http-server/src/main/resources/ssl/server-keystore.p12 -storepass qwe123 -rfc
```

## Create truststore with exported certificate for client

```bash
keytool -v -importcert -file http-server/src/main/resources/ssl/server.cer -alias server -keystore http-client/src/main/resources/ssl/server-truststore.p12 -storepass qwe123 -noprompt
```

## Fill "application.yaml" out like this:

```yaml
server:
  port: 8443
  ssl:
    enabled: true
    key-store: server-keystore.p12
    key-store-password: qwe123
    key-password: qwe123
```

### Testing
```bash
curl https://localhost:8443/api/v1/ssl -XGET -v --insecure
```

# Http client

## Create keystore

```bash
keytool -v -genkeypair -dname "CN=Russia,OU=Ryazan,O=DEv,C=RU" -keystore http-client/src/main/resources/ssl/client-keystore.p12 -storepass qwe123 -keypass qwe123 -keyalg RSA -keysize 2048 -alias client -validity 99999 -deststoretype pkcs12 -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement -ext ExtendedKeyUsage=serverAuth,clientAuth -ext SubjectAlternativeName:c=DNS:localhost,DNS:raspberrypi.local,IP:127.0.0.1
```

## Export certificate

```bash
keytool -v -exportcert -file http-client/src/main/resources/ssl/client.cer -alias client -keystore http-client/src/main/resources/ssl/client-keystore.p12 -storepass qwe123 -rfc
```

## Create truststore with exported certificate for server

```bash
keytool -v -importcert -file http-client/src/main/resources/ssl/client.cer -alias client -keystore http-server/src/main/resources/ssl/client-truststore.p12 -storepass qwe123 -noprompt
```

## Testing

### After client set up launch client module and make call bellow

```bash
curl http://localhost:8080/api/v1/launch -XGET -v
```
# ⚙️ Bloxy Api

![travis](https://travis-ci.org/GoodforGod/bloxy-api.svg?branch=master)
[![codecov](https://codecov.io/gh/GoodforGod/bloxy-api/branch/master/graph/badge.svg)](https://codecov.io/gh/GoodforGod/bloxy-api)

Kotlin & Java Library for all available [Bloxy](https://bloxy.info) API endpoints.

[Readme Web Page](https://goodforgod.github.io/bloxy-api/)

## Dependency :rocket:
**Maven**
```xml
<dependency>
    <groupId>com.github.goodforgod</groupId>
    <artifactId>bloxy-api</artifactId>
    <version>1.0.0</version>
</dependency>
```

**Gradle**
```groovy
dependencies {
    compile 'com.github.goodforgod:bloxy-api:1.0.0'
}
```

## Content
- [Custom HttpClient](#custom-httpclient)
- [API examples](#api-examples)
    - [Token](#token-api)
    - [Address](#address-api)
    - [DEX](#dex-api)
    - [Token Sale](#token-sale-api)
    - [Money Flow](#money-flow-api)
    - [Transaction](#transaction-api)
- [Version History](#version-history)

## Custom HttpClient

In case you need to set custom timeout, custom headers or better implementation for HttpClient, 
just implement **IHttpClient** by your self or initialize it with your values.

*Java*
```java
Supplier<IHttpExecutor> clientSupplier = () -> new HttpExecutor(10000, 40000);
BloxyApi api = new BloxyApi("YourApiKey", supplier);
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey", Supplier { HttpClient(6000, 10000) })
```

## API Examples

Below there are examples for each API module.

You can read about all API available [here at Bloxy](https://bloxy.info/api_methods)

### Token Api
**Get token holders**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<Holder> holders = api.getToken().holders("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280");
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val holders = api.token.holders("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280")
```

### Address Api
**Get address balance**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
Balance balance = api.getAddress().balance("0xB97048628DB6B661D4C2aA833e95Dbe1A905B280");
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val balance = api.address.balance("0x9eAb08daA285183F9A04269747D4125F08e634B0")
```

### DEX Api
**Get DEX protocols**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<DexProtocol> protocols = api.getDex().protocols();
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val protocols = api.dex.protocols()
```

### Token Sale Api
**Get current token sales**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<Sale> sales = api.getTokenSale().sales();
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val sales = api.tokenSale.sales()
```

### Money Flow Api
**Get tx detailds with proxy endpoint**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<String> addresses = Collections.singletonList("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949");
List<AddrTransfer> transfers = api.getMoneyFlow().transfersAll(addresses);
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val addresses = listOf("0xC0ea08A2d404d3172d2AdD29A45be56dA40e2949")
val result = api.moneyFlow.transfersAll(addresses)
```

### Transaction Api
**Statistic about last price**

*Java*
```java
BloxyApi api = new BloxyApi("YourApiKey");
List<String> txhashs= Collections.singletonList("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986");
List<TxTransfer> txTransfers = api.getTx().transfers(txhashs);
```

*Kotlin*
```kotlin
val api = BloxyApi("YourApiKey")
val txhashs = listOf("0x52a9a7dfe6f002b7d7deb5555e356e319839fc4dc280a68de55778524a41f986")
val transfers = api.tx.transfers(list)
```

## Version History

**1.0.0** - Initial project with all API functionality, with tests coverage for all cases.

## License

This project is licensed under the MIT - see the [LICENSE](LICENSE) file for details.
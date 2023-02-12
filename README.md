# API for Collecting and Analyzing Stock Statistics
#### Our API allows access to real-time stock price data and performs analysis of the data.  This API is perfect for investors, traders, and analysts who want to receive real-time information and perform analysis based on actual data.


## API Documentation

### Base URL: `Backend-project-v1.fly.dev`

### Get Stock by Date

Retrieve stock data for a specific symbol and date.

#### Endpoint

`/stock/date`

#### Query Parameters
- `symbol` (required): A string representing the stock symbol, for example `AAPL` for Apple Inc.
- `date` (required): A date in the format `YYYY-MM-DD` that specifies the date for which to retrieve stock data.

#### Response

##### Status Codes

- `200 OK`: The request was successful and a response body is returned.
- `400 Bad Request`: The request was malformed or invalid.
- `404 Not Found`: The specified stock symbol or date was not found in the database.

##### Response Body (200 OK)

```json
{
  "_id": {
    "symbol": "AAPL",
    "date": "1970-01-01"
  },
  "close": 93.00
}
`````

##### Properties
- `symbol`: A string representing the stock symbol.
- `date`: A date in the format `YYYY-MM-DD` that specifies the date for which the data was retrieved.
- `close`: The stock's closing price on the specified date.


#### Endpoint

### GET Stock by Period

Retrieve stock data for a specific symbol for a specified date range.

#### Endpoint URL:
`/stock/period`

#### Request
Method: `GET`

##### Query Parameters:
- `symbol` (required): A string representing the stock symbol. For example, `AAPL` for Apple Inc.
- `dateFrom` (required): A date in the format `YYYY-MM-DD` that specifies the start of the date range for which to retrieve stock data.
- `dateTo` (required): A date in the format `YYYY-MM-DD` that specifies the end of the date range for which to retrieve stock data.

#### Response

##### Status Codes:
- 200 OK: The request was successful, and a response body is returned.
- 400 Bad Request: The request was malformed or invalid.
- 404 Not Found: The specified stock symbol or date was not found in the database.

##### Response Body 200 OK:
```json
[
{
  "_id": {
    "symbol": "AAPL",
    "date": "1970-01-01"
  },
  "close": 93.00
},
{
  "_id": {
    "symbol": "AAPL",
    "date": "1970-01-02"
  },
  "close": 93.32
}, ...
]
`````
##### Properties
- `symbol`: A string representing the stock symbol.
- `date`: A date in the format `YYYY-MM-DD` that specifies the date for which the data was retrieved.
- `close`: The stock's closing price on the specified date.

#### Endpoint

### GET MIN close price

Retrieve the stock data with the lowest `close` price for a specific symbol across all dates.

#### Endpoint URL:
`/stock/min `

#### Request
Method: `GET`

##### Query Parameters:
- symbol (required): A string representing the stock symbol. For example, `AAPL` for Apple Inc.

#### Response

##### Status Codes:
- 200 OK: The request was successful, and a response body is returned.
- 400 Bad Request: The request was malformed or invalid.
- 404 Not Found: The specified stock symbol or date was not found in the database.

##### Response Body 200 OK:
```json
{
  "_id": {
    "symbol": "HOOD",
    "date": "2022-06-16"
  },
  "close": 6.89
}
`````
##### Properties
- `symbol`: A string representing the stock symbol.
- `date`: A date in the format `YYYY-MM-DD` that specifies the date for which the data was retrieved.
- `close`: The stock's closing price on the specified date.

#### Endpoint

### GET MAX close price

Retrieve the stock data with the highest `close` price for a specific symbol across all dates.

#### Endpoint URL:
`/stock/max `

#### Request
Method: `GET`

##### Query Parameters:
- symbol (required): A string representing the stock symbol. For example, `AAPL` for Apple Inc.

#### Response

##### Status Codes:
- 200 OK: The request was successful, and a response body is returned.
- 400 Bad Request: The request was malformed or invalid.
- 404 Not Found: The specified stock symbol or date was not found in the database.

##### Response Body 200 OK:
```json
{
  "_id": {
    "symbol": "HOOD",
    "date": "1970-01-01"
  },
  "close": 70.389999
}
`````
##### Properties
- `symbol`: A string representing the stock symbol.
- `date`: A date in the format `YYYY-MM-DD` that specifies the date for which the data was retrieved.
- `close`: The stock's closing price on the specified date.


#### Endpoint

### GET MAX and MIN year profit

Retrieve the stock data with the highest and lowest profit across a specified symbol and date range, grouped
into periods of a specified number of years.

#### Endpoint URL:
`/stock/profit`

#### Request
Method: `GET`

##### Query Parameters:
- `symbol` (required): A string representing the stock symbol. For example, `AAPL` for Apple Inc.
- `fromDate` (required): A date in the format `YYYY-MM-DD` that specifies the start of the date range
for which to retrieve stock data.
- `toDate` (required): A date in the format `YYYY-MM-DD` that specifies the end of the date range for
which to retrieve stock data.
- `periodInYears` (required):  An integer that specifies the number of years each period should cover

#### Response

##### Status Codes:
- 200 OK: The request was successful, and a response body is returned.
- 400 Bad Request: The request was malformed or invalid.
- 404 Not Found: The specified stock symbol or date was not found in the database.

##### Response Body 200 OK:
```json
[
    {
        "symbol": "AMZN",
        "dateFrom": "2000-02-02",
        "dateTo": "2005-02-02",
        "closeStart": 4.209375,
        "closeEnd": 1.7875,
        "yearProfit": -15.74
    },
    {
        "symbol": "AMZN",
        "dateFrom": "2008-11-19",
        "dateTo": "2013-11-20",
        "closeStart": 1.7515,
        "closeEnd": 18.445999,
        "yearProfit": 60.14
    }
]
`````
##### Properties

- `symbol`: A string representing the stock symbol.
- `dateFrom`: A date in the format `YYYY-MM-DD` specifies the start of the date range.
- `dateTo`:  A date in the format `YYYY-MM-DD` specifies the end of the date range.
- `closeStart`: The stock's closing price at the start of this period.
- `closeEnd`: The stock's closing price at the end of this period.
- `yearProfit`: The highest or lowest profit achieved in this period.

#### Endpoint

### GET correlation

Retrieve the correlation between two stock symbols over a specified date range.

#### Endpoint URL:
`/stock/correlation`

#### Request
Method: `GET`

##### Query Parameters:
- `fromDate` (required): A date in the format `YYYY-MM-DD` that specifies the start date for the data to be retrieved.
- `toDate` (required): A date in the format `YYYY-MM-DD` that specifies the end date for the data to be retrieved.
-	`firstSymbol` (required): A string representing the first stock symbol.
-	`secondSymbol` (required): A string representing the second stock symbol.

#### Response

##### Status Codes:
- 200 OK: The request was successful, and a response body is returned.
- 400 Bad Request: The request was malformed or invalid.
- 404 Not Found: The specified stock symbol or date was not found in the database.

##### Response Body 200 OK:
```json
{
        "firstSymbol": "HOOD",
        "secondSymbol": "VII",
        "correlation": -0.51,
        "message": "Neglible correlation",
}
`````
##### Properties
- `firstSymbol`: A string representing the first stock symbol.
- `secondSymbol`: A string representing the second stock symbol.
- `correlation`:  A decimal number representing the correlation between the two stock symbols, where 1 indicates a perfect positive correlation, -1 - --- `indicates` a perfect negative correlation, and 0 indicates no correlation.
- `message`: Interprets the result of the correlation calculation, depending on the result.


#### Endpoint

### GET all available symbols

#### Endpoint URL:
`/stock/symbols`

#### Response

##### Response Body (200 OK)

```json
["APPL", "ADBE", "VII", "HOOD"]
`````



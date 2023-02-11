# API for Collecting and Analyzing Stock Statistics
### Our API allows access to real-time stock price data and performs analysis of the data.  This API is perfect for investors, traders, and analysts who want to receive real-time information and perform analysis based on actual data.


## API Documentation

Base URL: `Backend-project-v1.fly.dev`

### Get Stock by Date

Retrieve stock data for a specific symbol and date.

#### Endpoint

`GET /stock/{symbol}/date`

#### Path Parameters

- `symbol` (required): A string representing the stock symbol, for example `AAPL` for Apple Inc.

#### Query Parameters

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
    "date": "1970-01-01T22:00:00.000+00:00"
  },
  "close": 93.00
}
`````

##### Properties
- `symbol`: A string representing the stock symbol.
- `date`: A date in the format YYYY-MM-DDT00:00:00.000+00:00 that specifies the date for which the data was retrieved.
- `close`: The stock's closing price on the specified date.

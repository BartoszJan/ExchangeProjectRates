# ExchangeProjectRates

Application is based on Java, PostgreSQL database and Hibernate.
The program is used to display currency exchange rates in a given time interval in a line chart and forecast exchange rates for the next 30 days.
Graphic interface is made in Java Swing and GUIForm.
The scanner searches the "Data Files" folder until the xml file "Rates Table" from npb.pl website appears. 
When the file appears, the table of courses is saving into the database. 
In the panel of current rates, the given currency and date range are selected and a graph is displayed according to the defined parameters. 
In the rate forecast panel, the currency is selected from: Euro, Dollar, Pound and Franc and a line chart of the exchange rate forecast for the next 30 days is displayed (data are downloaded from the "www.usdforecast.com" website).

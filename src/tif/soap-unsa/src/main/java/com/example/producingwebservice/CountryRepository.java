package com.example.producingwebservice;

import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.text.DecimalFormat;

import unsa.sd.soap.Country;
import unsa.sd.soap.Currency;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CountryRepository {
	private static final Map<String, Currency> currencies = new HashMap<>();
	private static final Map<String, Country> countries = new HashMap<>();
	@PostConstruct
	public void initData() {

		// Datos de monedas de cambio
		Currency c_peru = new Currency();
		c_peru.setType("PEN");
		c_peru.setValue(0.26);
		currencies.put(c_peru.getType(), c_peru);

		Currency c_argentina = new Currency();
		c_argentina.setType("ARS");
		c_argentina.setValue(0.0011);
		currencies.put(c_argentina.getType(), c_argentina);

		Currency c_mexico = new Currency();
		c_mexico.setType("MXN");
		c_mexico.setValue(0.056);
		currencies.put(c_mexico.getType(), c_mexico);

		Currency c_brasil = new Currency();
		c_brasil.setType("BRL");
		c_brasil.setValue(0.18);
		currencies.put(c_brasil.getType(), c_brasil);

		Currency c_usa = new Currency();
		c_usa.setType("USD");
		c_usa.setValue(1.0);
		currencies.put(c_usa.getType(), c_usa);

		Currency c_rusia = new Currency();
		c_rusia.setType("RUB");
		c_rusia.setValue(0.011);
		currencies.put(c_rusia.getType(), c_rusia);

		Currency c_chile = new Currency();
		c_chile.setType("CLP");
		c_chile.setValue(0.0011);
		currencies.put(c_chile.getType(), c_chile);

		Currency c_reino_unido = new Currency();
		c_reino_unido.setType("GBP");
		c_reino_unido.setValue(1.28);
		currencies.put(c_reino_unido.getType(), c_reino_unido);

		Currency c_euro = new Currency();
		c_euro.setType("EUR");
		c_euro.setValue(1.08);
		currencies.put(c_euro.getType(), c_euro);

		Currency c_china = new Currency();
		c_china.setType("CNY");
		c_china.setValue(0.14);
		currencies.put(c_china.getType(), c_china);

		Currency c_bolivia = new Currency();
		c_bolivia.setType("BOB");
		c_bolivia.setValue(0.14);
		currencies.put(c_bolivia.getType(), c_bolivia);

		// Datos de paises
		Country peru = new Country();
		peru.setName("Perú");
		peru.setCapital("Lima");
		peru.setCurrency("PEN");
		countries.put(peru.getName(), peru);

		Country argentina = new Country();
		argentina.setName("Argentina");
		argentina.setCapital("Buenos Aires");
		argentina.setCurrency("ARS");
		countries.put(argentina.getName(), argentina);

		Country mexico = new Country();
		mexico.setName("México");
		mexico.setCapital("Ciudad de México");
		mexico.setCurrency("MXN");
		countries.put(mexico.getName(), mexico);

		Country brasil = new Country();
		brasil.setName("Brasil");
		brasil.setCapital("Brasilia");
		brasil.setCurrency("BRL");
		countries.put(brasil.getName(), brasil);

		Country estados_unidos = new Country();
		estados_unidos.setName("Estados Unidos");
		estados_unidos.setCapital("Washington D.C.");
		estados_unidos.setCurrency("USD");
		countries.put(estados_unidos.getName(), estados_unidos);

		Country rusia = new Country();
		rusia.setName("Rusia");
		rusia.setCapital("Moscú");
		rusia.setCurrency("RUB");
		countries.put(rusia.getName(), rusia);

		Country chile = new Country();
		chile.setName("Chile");
		chile.setCapital("Santiago");
		chile.setCurrency("CLP");
		countries.put(chile.getName(), chile);

		Country reino_unido = new Country();
		reino_unido.setName("Reino Unido");
		reino_unido.setCapital("Londres");
		reino_unido.setCurrency("GBP");
		countries.put(reino_unido.getName(), reino_unido);

		Country china = new Country();
		china.setName("China");
		china.setCapital("Pekín");
		china.setCurrency("CNY");
		countries.put(china.getName(), china);

		Country bolivia = new Country();
		bolivia.setName("Bolivia");
		bolivia.setCapital("La Paz");
		bolivia.setCurrency("BOB");
		countries.put(bolivia.getName(), bolivia);

	}

	public Country findCountry(String name) {
		Assert.notNull(name, "El nombre del pais no debe ser nulo");
		return countries.get(name);
	}

	public Currency findCurrency(String country_name) {
		Assert.notNull(country_name, "El nombre del pais no debe ser nulo");

		Country country = findCountry(country_name);
		String currency_code = country.getCurrency();


		return currencies.get(currency_code);
	}

	public double exchange_currency(String country_1, String country_2) {
		Assert.notNull(country_1, "El nombre del país no debe ser nulo");
		Assert.notNull(country_2, "El nombre del país no debe ser nulo");

		double val1 = findCurrency(country_1).getValue();
		double val2 = findCurrency(country_2).getValue();

		double exchangeRate = val1 / val2;

		// Formatear el resultado a dos decimales
		DecimalFormat df = new DecimalFormat("#.##");
		exchangeRate = Double.parseDouble(df.format(exchangeRate));

		return exchangeRate;
	}
}

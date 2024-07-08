package com.example.producingwebservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import unsa.sd.soap.GetCountryRequest;
import unsa.sd.soap.GetCountryResponse;
import unsa.sd.soap.GetCurrencyRequest;
import unsa.sd.soap.GetCurrencyResponse;
import unsa.sd.soap.GetExchangeRequest;
import unsa.sd.soap.GetExchangeResponse;

@Endpoint
public class CountryEndpoint {
	private static final String NAMESPACE_URI = "soap.sd.unsa";

	private CountryRepository countryRepository;

	@Autowired
	public CountryEndpoint(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
	@ResponsePayload
	public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
		GetCountryResponse response = new GetCountryResponse();
		response.setCountry(countryRepository.findCountry(request.getName()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCurrencyRequest")
	@ResponsePayload
	public GetCurrencyResponse getCurrency(@RequestPayload GetCurrencyRequest request) {
		GetCurrencyResponse response = new GetCurrencyResponse();
		response.setCurrency(countryRepository.findCurrency(request.getName()));

		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getExchangeRequest")
	@ResponsePayload
	public GetExchangeResponse getExchange(@RequestPayload GetExchangeRequest request) {
		GetExchangeResponse response = new GetExchangeResponse();
		response.setExchange(countryRepository.exchange_currency(request.getNameFirst(), request.getNameSecond()));

		return response;
	}
}

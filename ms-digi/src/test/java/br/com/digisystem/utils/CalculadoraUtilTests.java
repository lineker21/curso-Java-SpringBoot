package br.com.digisystem.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CalculadoraUtilTests {
	
	private CalculadoraUtil calculadoraUtil = new CalculadoraUtil();
	
	@Test
	void somarTest() {
		
		// temos que ter o valor esperado e o valor atual
		
		int a = 9;
		int b = 5;
		int esperado = 14;
		
		int resultadoAtual = calculadoraUtil.somar(a, b);
		
		assertEquals(esperado, resultadoAtual);
	}

}

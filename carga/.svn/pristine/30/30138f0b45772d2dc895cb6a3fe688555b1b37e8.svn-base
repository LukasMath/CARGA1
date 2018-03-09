package carga.validacao.factory;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import carga.validacao.util.EmailUtil;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import freemarker.template.Version;

public class FreemarkerEmailFactory {

	private Template template;

	private static FreemarkerEmailFactory instace;

	private FreemarkerEmailFactory() throws TemplateNotFoundException,
			MalformedTemplateNameException, ParseException, IOException {

		Configuration cfg = new Configuration(new Version("2.3.23"));

		cfg.setClassForTemplateLoading(FreemarkerEmailFactory.class,
				"/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		template = cfg.getTemplate("email.ftl");
	}

	public static FreemarkerEmailFactory getInstance()
			throws TemplateNotFoundException, MalformedTemplateNameException,
			ParseException, IOException {

		if (instace == null)
			instace = new FreemarkerEmailFactory();

		return instace;

	}

	public String pegarHTMLFreeMarker(Map<String, List<String>> log)
			throws TemplateException, IOException {
		String retorno = null;
		StringWriter out = new StringWriter();

		try {
			Map<String, Object> map = new HashMap<>();
			map.put("log", log);

			template.process(map, out);
			retorno = out.getBuffer().toString();
			


		} catch (Exception e) {
			System.out.println("Erro ao pegarHTMLFreeMarker:.\n"
					+ e.getMessage());
		}
		return retorno;
	}



}

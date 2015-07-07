package org.macula.extension.finder;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.macula.ApplicationContext;
import org.macula.base.security.util.SecurityUtils;
import org.macula.core.mvc.view.FreeMarkerViewImpl;
import org.macula.plugins.mda.finder.domain.FinderSchema;
import org.macula.plugins.mda.finder.domain.FinderStaticParam;
import org.macula.plugins.mda.finder.service.FinderSchemaService;
import org.macula.plugins.mda.finder.view.FinderView;
import org.macula.plugins.mda.finder.vo.FinderViewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

public class FinderDialogView extends FreeMarkerViewImpl {

	private final Logger log = LoggerFactory.getLogger(FinderView.class);
	private final String code;
	private final String tabViewCode;
	private final FinderSchemaService schemaRepository;

	private final List<FinderStaticParam> staticParams;

	public FinderDialogView(String code) {
		this(code, (String) null);
	}

	public FinderDialogView(String code, String tab) {
		this(code, tab, null);
	}

	public FinderDialogView(String code, List<FinderStaticParam> staticParams) {
		this(code, null, staticParams);
	}

	public FinderDialogView(String code, String tab, List<FinderStaticParam> staticParams) {
		this.code = code;
		this.setApplicationContext(ApplicationContext.getContainer());
		schemaRepository = getApplicationContext().getBean(FinderSchemaService.class);
		this.setContentType("text/html;charset=UTF-8");
		this.setExposeSpringMacroHelpers(true);
		FinderSchema schema = schemaRepository.get(code);
		this.tabViewCode = schema.getTabViewCode(tab);

		this.staticParams = staticParams;
	}

	@Override
	protected void exposeHelpers(Map<String, Object> model, HttpServletRequest request) throws Exception {
		FinderSchema schema = getSchema();
		if (schema == null) {
			log.error("Schema not found! " + code);
			throw new IllegalArgumentException(code);
		}
		FinderViewModel result = new FinderViewModel(schema, tabViewCode, SecurityUtils.getUserContext(), staticParams);
		model.put("relativePath", schema.getRelativePath());
		model.put("viewModel", result);

		if (log.isDebugEnabled()) {
			log.debug(code + " relativePath " + schema.getRelativePath());
			log.debug(code + " result " + result);
		}
		super.exposeHelpers(model, request);
	}

	@Override
	public String getUrl() {
		String url = ClassUtils.convertClassNameToResourcePath(getClass().getName()) + ".ftl";
		if (log.isDebugEnabled()) {
			log.debug("Load finder view template from " + url);
		}
		return url;
	}

	protected FinderSchema getSchema() {
		return schemaRepository.get(code);
	}

}

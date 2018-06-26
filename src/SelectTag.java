import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 用于
 */
public class SelectTag implements TemplateMethodModelEx {

	@Override
	public String exec(List arguments) throws TemplateModelException {
		
		//-分割{'key':'status','name':'状态'}
		return "{'key':'member','name':'会员级别'}";
	}

}

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;


public class ControllerMapTag implements TemplateMethodModelEx {

	@Override
	public String exec(List arguments) throws TemplateModelException {
		//-分割{'key':'status','name':[{'key':'1','name':'正常'},{'key':'2','name':'删除'}]}
		return "{'key':'member','name':[{'key':'1','name':'铜牌'},{'key':'2','name':'银牌'},{'key':'3','name':'金牌'},{'key':'4','name':'钻石'},{'key':'5','name':'皇冠'}]}";
	}
}

import java.util.List;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 *
 */
public class ListMapTag implements TemplateMethodModelEx {

	@Override
	public String exec(List arguments) throws TemplateModelException {
		
		//HotelList 数据库hotel_list 表 全大写
		//hotelId     数据库hotel_id   字段  大写
		return "{'uploadsId':'Uploads'}";
	}

}

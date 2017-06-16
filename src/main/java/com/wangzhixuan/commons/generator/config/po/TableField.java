/**
 * Copyright (c) 2011-2020, hubin (jobob@qq.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wangzhixuan.commons.generator.config.po;

import com.wangzhixuan.commons.generator.config.StrategyConfig;

/**
 * <p>
 * 表字段信息
 * </p>
 * @author YangHu
 * @since 2016-12-03
 */
public class TableField {
	private boolean keyFlag;
	private String name;
	private String type;
	private String propertyName;
	private String propertyType;
	private String comment;

	public boolean isKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(boolean keyFlag) {
		this.keyFlag = keyFlag;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isConvert() {
		if (StrategyConfig.DB_COLUMN_UNDERLINE) {
			return false;
		}
		return !name.equalsIgnoreCase(propertyName);
	}

	public String getCapitalName() {
		return propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
	}

}

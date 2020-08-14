package org.openapitools.model;

import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;
import javax.validation.Valid;

import io.swagger.annotations.ApiModelProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import com.fasterxml.jackson.annotation.JsonProperty;

@XmlAccessorType(XmlAccessType.FIELD)
 @XmlType(name = "GroupStub", propOrder =
    { "name", "description", "members", "disable"
})

@XmlRootElement(name="GroupStub")
public class GroupStub  {
  
  @XmlElement(name="name")
  @ApiModelProperty(value = "")
  private String name;

  @XmlElement(name="description")
  @ApiModelProperty(value = "")
  private String description;

  @XmlElement(name="members")
  @ApiModelProperty(value = "")
  private List<String> members = null;

  @XmlElement(name="disable")
  @ApiModelProperty(value = "")
  private Boolean disable;
 /**
   * Get name
   * @return name
  **/
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public GroupStub name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Get description
   * @return description
  **/
  @JsonProperty("description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public GroupStub description(String description) {
    this.description = description;
    return this;
  }

 /**
   * Get members
   * @return members
  **/
  @JsonProperty("members")
  public List<String> getMembers() {
    return members;
  }

  public void setMembers(List<String> members) {
    this.members = members;
  }

  public GroupStub members(List<String> members) {
    this.members = members;
    return this;
  }

  public GroupStub addMembersItem(String membersItem) {
    this.members.add(membersItem);
    return this;
  }

 /**
   * Get disable
   * @return disable
  **/
  @JsonProperty("disable")
  public Boolean getDisable() {
    return disable;
  }

  public void setDisable(Boolean disable) {
    this.disable = disable;
  }

  public GroupStub disable(Boolean disable) {
    this.disable = disable;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GroupStub {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    members: ").append(toIndentedString(members)).append("\n");
    sb.append("    disable: ").append(toIndentedString(disable)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private static String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}


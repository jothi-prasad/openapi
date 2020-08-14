package org.openapitools.model;

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
 @XmlType(name = "HeadlessUserStub", propOrder =
    { "name", "property", "email", "disable"
})

@XmlRootElement(name="HeadlessUserStub")
public class HeadlessUserStub  {
  
  @XmlElement(name="name")
  @ApiModelProperty(value = "")
  private String name;

  @XmlElement(name="property")
  @ApiModelProperty(value = "")
  private String property;

  @XmlElement(name="email")
  @ApiModelProperty(value = "")
  private String email;

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

  public HeadlessUserStub name(String name) {
    this.name = name;
    return this;
  }

 /**
   * Get property
   * @return property
  **/
  @JsonProperty("property")
  public String getProperty() {
    return property;
  }

  public void setProperty(String property) {
    this.property = property;
  }

  public HeadlessUserStub property(String property) {
    this.property = property;
    return this;
  }

 /**
   * Get email
   * @return email
  **/
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public HeadlessUserStub email(String email) {
    this.email = email;
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

  public HeadlessUserStub disable(Boolean disable) {
    this.disable = disable;
    return this;
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class HeadlessUserStub {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    property: ").append(toIndentedString(property)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
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


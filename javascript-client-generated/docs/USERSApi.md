# YanisUserApi.USERSApi

All URIs are relative to *http://petstore.swagger.io/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**create**](USERSApi.md#create) | **POST** /users | Create a new user in Yanis
[**listUsers**](USERSApi.md#listUsers) | **GET** /users | List all Yanis users
[**showPetById**](USERSApi.md#showPetById) | **GET** /user/{userId} | get a single user by ID

<a name="create"></a>
# **create**
> create()

Create a new user in Yanis

### Example
```javascript
import YanisUserApi from 'yanis_user_api';

let apiInstance = new YanisUserApi.USERSApi();
apiInstance.create((error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully.');
  }
});
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="listUsers"></a>
# **listUsers**
> Users listUsers(opts)

List all Yanis users

### Example
```javascript
import YanisUserApi from 'yanis_user_api';

let apiInstance = new YanisUserApi.USERSApi();
let opts = { 
  'limit': 56 // Number | How many users to fetch at single stretch (max 100)
};
apiInstance.listUsers(opts, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **limit** | **Number**| How many users to fetch at single stretch (max 100) | [optional] 

### Return type

[**Users**](Users.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/xml

<a name="showPetById"></a>
# **showPetById**
> Users showPetById(userId)

get a single user by ID

### Example
```javascript
import YanisUserApi from 'yanis_user_api';

let apiInstance = new YanisUserApi.USERSApi();
let userId = 56; // Number | User to be retrieved..

apiInstance.showPetById(userId, (error, data, response) => {
  if (error) {
    console.error(error);
  } else {
    console.log('API called successfully. Returned data: ' + data);
  }
});
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **userId** | **Number**| User to be retrieved.. | 

### Return type

[**Users**](Users.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json, application/xml


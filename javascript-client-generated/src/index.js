/**
 * Yanis User API
 * No description provided (generated by Swagger Codegen https://github.com/swagger-api/swagger-codegen)
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *
 */

import ApiClient from './ApiClient';
import Error from './model/Error';
import User from './model/User';
import Users from './model/Users';
import USERSApi from './api/USERSApi';

/**
* Object.<br>
* The <code>index</code> module provides access to constructors for all the classes which comprise the public API.
* <p>
* An AMD (recommended!) or CommonJS application will generally do something equivalent to the following:
* <pre>
* var YanisUserApi = require('index'); // See note below*.
* var xxxSvc = new YanisUserApi.XxxApi(); // Allocate the API class we're going to use.
* var yyyModel = new YanisUserApi.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* <em>*NOTE: For a top-level AMD script, use require(['index'], function(){...})
* and put the application logic within the callback function.</em>
* </p>
* <p>
* A non-AMD browser application (discouraged) might do something like this:
* <pre>
* var xxxSvc = new YanisUserApi.XxxApi(); // Allocate the API class we're going to use.
* var yyy = new YanisUserApi.Yyy(); // Construct a model instance.
* yyyModel.someProperty = 'someValue';
* ...
* var zzz = xxxSvc.doSomething(yyyModel); // Invoke the service.
* ...
* </pre>
* </p>
* @module index
* @version 1.0.0
*/
export {
    /**
     * The ApiClient constructor.
     * @property {module:ApiClient}
     */
    ApiClient,

    /**
     * The Error model constructor.
     * @property {module:model/Error}
     */
    Error,

    /**
     * The User model constructor.
     * @property {module:model/User}
     */
    User,

    /**
     * The Users model constructor.
     * @property {module:model/Users}
     */
    Users,

    /**
    * The USERSApi service constructor.
    * @property {module:api/USERSApi}
    */
    USERSApi
};

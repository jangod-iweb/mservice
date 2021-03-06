const originName="mapp";
const originPluginName="demo";
const originPackageName="org.jangod.mapp";


function formatFirstToUpper(str) {
  return str.trim().toLowerCase().replace(str[0], str[0].toUpperCase());
}
function formatPackage(packageName){
  return packageName.replace(/\./g,"/");
}
function formatFileName(originFileName,prompts){
  let fileName = originFileName;
  const name = prompts.name;
  const cOriginName = formatFirstToUpper(originName);
  const cName = formatFirstToUpper(name);
  const originPackagePath = formatPackage(originPackageName);
  const packageName = prompts.packageName;
  const packagePath = formatPackage(packageName);
  const pluginName = prompts.pluginName;
  const cOriginPluginName = formatFirstToUpper(originPluginName);
  const cPluginName = formatFirstToUpper(pluginName);

  if(fileName.indexOf(`${originPackagePath}/`)>-1){
    fileName = fileName.replace(`${originPackagePath}/`,`${packagePath}/`);
  }

  if(fileName.indexOf(`${originName}-plugin-runner/`)==0){
    fileName = fileName.replace(`${originName}-plugin-runner/`,`${name}-runner/`);

    if(fileName.indexOf(`${cOriginName}Application.java`)>-1){
      fileName = fileName.replace(`${cOriginName}Application.java`,`${cName}RunnerApplication.java`);
    }
  }
  else if(fileName.indexOf(`${originName}-plugin/`)==0){
    fileName = fileName.replace(`${originName}-plugin/`,`${name}-plugin/`)

    if(fileName.indexOf(`${originName}-plugin-${originPluginName}/`)>-1){
      fileName = fileName.replace(`${originName}-plugin-${originPluginName}/`,`${name}-plugin-${pluginName}/`)
    }

    if(fileName.indexOf(`${packagePath}/${originPluginName}/`)>-1){
      fileName = fileName.replace(`${packagePath}/${originPluginName}/`,`${packagePath}/${pluginName}/`)
    }

    if(fileName.indexOf(`${cOriginName}${cOriginPluginName}Plugin.java`)>-1){
      fileName = fileName.replace(`${cOriginName}${cOriginPluginName}Plugin.java`,`${cName}${cPluginName}Plugin.java`);
    }
    if(fileName.indexOf(`${originName}-plugin-${originPluginName}.yml`)>-1){
      fileName = fileName.replace(`${originName}-plugin-${originPluginName}.yml`,`${name}-plugin-${pluginName}.yml`);
    }
  }
  else if(fileName.indexOf(`${originName}-api/`)==0){
    fileName = fileName.replace(`${originName}-api/`,`${name}-api/`)

    if(fileName.indexOf(`${originName}-api-${originPluginName}/`)>-1){
      fileName = fileName.replace(`${originName}-api-${originPluginName}/`,`${name}-api-${pluginName}/`)
    }

    if(fileName.indexOf(`${packagePath}/${originPluginName}/`)>-1){
      fileName = fileName.replace(`${packagePath}/${originPluginName}/`,`${packagePath}/${pluginName}/`)
    }
  }


  return fileName;
}
module.exports = {
  "prompts": {
    "name": {
      "type": "string",
      "required": true,
      "message": "????????????",
      "default": "mapp"
    },
    "packageName": {
      "type": "string",
      "required": true,
      "message": "???????????????",
      "default": (answers)=>{
        return "org.jangod."+answers.name
      }
    },
    "pluginName": {
      "type": "string",
      "required": true,
      "message": "??????????????????",
      "default": "demo"
    },
    "backendPort": {
      "type": "int",
      "required": true,
      "message": "???????????????",
      "default": 9302
    },
    "nacosAddr": {
      "type": "string",
      "required": true,
      "message": "nacos??????????????????",
      "default": "127.0.0.1:8848"
    },
    "nacosUsername": {
      "type": "string",
      "required": true,
      "message": "nacos?????????",
      "default": "nacos"
    },
    "nacosPassword": {
      "type": "string",
      "required": true,
      "message": "nacos??????",
      "default": "nacospassword"
    },
    "nacosNamespace": {
      "type": "string",
      "required": true,
      "message": "nacos????????????",
      "default": "nacosnamespace"
    },
    "redisAddr": {
      "type": "string",
      "required": true,
      "message": "redis??????",
      "default": "127.0.0.1"
    },
    "redisPort": {
      "type": "int",
      "required": true,
      "message": "redis?????????",
      "default": 6379
    },
    "redisDatabase": {
      "type": "int",
      "required": true,
      "message": "redis??????",
      "default": 9
    },
    "redisPassword": {
      "type": "int",
      "required": true,
      "message": "redis??????",
      "default": ""
    },
    "mqttAddr": {
      "type": "string",
      "required": true,
      "message": "mqtt????????????",
      "default": "ws://203.175.130.170:8083/mqtt"
    },
    "mqttUsername": {
      "type": "string",
      "required": true,
      "message": "mqtt?????????",
      "default": ""
    },
    "mqttPassword": {
      "type": "string",
      "required": true,
      "message": "mqtt??????",
      "default": ""
    },"mqttPassword": {
      "type": "string",
      "required": true,
      "message": "mqtt?????????id????????????????????????????????????",
      "default": "client_xxx_runner_01"
    },
    "isWeb": {
      "type": "confirm",
      "required": true,
      "message": "??????????????????",
      "default": false
    },
    "webPort": {
      "when": "isWeb",
      "type": "int",
      "message": "???????????????",
      "required": false,
      "default": 9301
    },
    "isWebDocs": {
      "type": "confirm",
      "required": true,
      "message": "????????????????????????",
      "default": false
    },
    "webDocPort": {
      "when": "isWebDocs",
      "type": "int",
      "message": "?????????????????????",
      "required": false,
      "default": 9303
    }
  },
  "filters": {
    "mapp-plugin/mapp-plugin-demo/web/**": "isWeb",
    "mapp-plugin/mapp-plugin-demo/web-docs/**": "isWebDocs"
  },
  helpers: {
    firstToUpper(str) {
      return formatFirstToUpper(str);
    },
    if_true(value1, value2, options){
      if(value1 && value2){
        return options.fn(this);
      }else{
        return options.inverse(this);
      }
    }
  },
  "metalsmith": {
    "after": function(metalsmith, opts, helpers){
        metalsmith.use((files, metalsmith, done)=> {
          let prompts = metalsmith._metadata;
          //  console.log("==",files)
          Object.keys(files).forEach(fileName => { //??????????????????
            const newFileName = formatFileName(fileName,prompts);
            if(newFileName != fileName){
              files[newFileName] = files[fileName];
              delete files[fileName];
            }
           })
          done()
        });
    }
  },
  "completeMessage": "??????????????????"
}

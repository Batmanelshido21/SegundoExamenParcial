/**
 * @license
 * Copyright (c) 2014, 2020, Oracle and/or its affiliates.
 * Licensed under The Universal Permissive License (UPL), Version 1.0
 * as shown at https://oss.oracle.com/licenses/upl/
 * @ignore
 */
/*
 * Your application specific code will go here
 */
require(['knockout', 'ojs/ojbootstrap', 'ojs/ojarraydataprovider', 'ojs/ojknockout', 'ojs/ojtable', 'ojs/ojdialog',
  'ojs/ojinputtext', 'ojs/ojformlayout', 'ojs/ojvalidationgroup', 'ojs/ojbutton', 'ojs/ojdatetimepicker'],
  function (ko, Bootstrap, ArrayDataProvider) {
    function ViewModel() {
      var self = this;

      var listdocs = [];

      self.dataprovider = ko.observable();

      self.groupValid = ko.observable();
      self.var_tel = ko.observable();
      self.var_contra = ko.observable();
      self.var_nom = ko.observable();
      self.var_Am = ko.observable();
      self.var_Ap = ko.observable();
      self.var_tele = ko.observable();
      self.var_fecha = ko.observable();
      self.var_pass = ko.observable();
      self.var_des = ko.observable();
      self.var_feI = ko.observable();
      self.var_feT = ko.observable();
      self.var_lug = ko.observable();
      var idUsuario;

      self.dataprovider(new ArrayDataProvider(listdocs, { keyAttributes: 'fechaInicio', implicitSort: [{ attribute: 'fechaInicio', direction: 'ascending' }] }));

      this.myActionFunction = function (event) {
        this.selectedMenuItem(event.target.textContent);
      }.bind(this);

      self.submit = function () {
        var valid = verificarCampos();
        if (valid) {
          login(obtenerNotas);
          //obtenerNotas();
          console.log(idUsuario);
          document.getElementById('modalDialog1').close();
        }
      }.bind(this);

      self.registrarEv = function () {
        var valid = verificarCampos();
        if (valid) {
          registrarEvento();
          document.getElementById('modalDialog3').close();
        }
      }.bind(this);

      self.registrarCuenta = function () {
        var valid = verificarCampos();
        if (valid) {
          registrarUsuario();
          document.getElementById('modalDialog2').close();
        }
      };

      self.abrirRegistroDeUsuario = function (event) {
        document.getElementById('modalDialog1').close();
        document.getElementById('modalDialog2').open();
      };

      self.regresar = function (event) {
        document.getElementById('modalDialog2').close();
        document.getElementById('modalDialog1').open();
      };

      self.cancelar = function (event) {
        document.getElementById('modalDialog3').close();
      };

      self.abrirRegistroEv = function (event) {
        document.getElementById('modalDialog3').open();
      };

      var verificarCampos = function () {
        var tracker = document.getElementById("tracker");
        if (tracker.valid === "valid") {
          return true;
        }
        else {
          tracker.showMessages();
          tracker.focusOn("@firstInvalidShown");
          return false;
        }
      };

      this.open = function (event) {
        document.getElementById('modalDialog1').open();
      };

      function registrarEvento(){
        var request = new XMLHttpRequest();
        var peticion = "descripcion=" + self.var_des() + "&fechaInicio" + self.var_feI() + "&fechaTermino=" + self.var_feT() + "&lugar=" + self.var_lug() + "&Usuario_idUsuario=" + idUsuario;
        request.open('POST', "http://localhost:8080/ExamenSegundoParcial/webresources/eventos/registroEvento", true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        alert(request.status);
        request.onload = function () {
          alert(request.status);
          if (request.status >= 200 && request.status < 300) {

            var data = JSON.parse(this.response);

            console.log(data);

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send(peticion);
      }

      function registrarUsuario() {
        var request = new XMLHttpRequest();
        var peticion = "nombre=" + self.var_nom() + "&apellidoP=" + self.var_Ap() + "&apellidoM=" + self.var_Ap() + "&numeroCelular=" + self.var_tele() + "&fechaNacimiento=" + self.var_fecha() + "&password=" + self.var_pass();
        request.open('POST', "http://localhost:8080/ExamenSegundoParcial/webresources/usuarios/registro", true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            var data = JSON.parse(this.response);

            console.log(data);

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send(peticion);
      }

      function login(fun) {
        var request = new XMLHttpRequest();
        var peticion = "numeroCelular=" + self.var_tel() + "&password=" + self.var_contra();
        request.open('POST', "http://localhost:8080/ExamenSegundoParcial/webresources/usuarios/login", true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            var data = JSON.parse(this.response);

            fun(data.usuario_idUsuario);

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send(peticion);
      }

      function obtenerNotas(id) {
        var request = new XMLHttpRequest();
        idUsuario = id;
        console.log("El id es: " + id);
        request.open('GET', "http://localhost:8080/ExamenSegundoParcial/webresources/eventos/getEventoUsuario/" + id, true);
        request.timeout = 6000; //milliseconds
        request.withCredentials = true;
        request.onload = function () {
          if (request.status >= 200 && request.status < 300) {

            listdocs = JSON.parse(this.response);
            self.dataprovider(new ArrayDataProvider(listdocs, { keyAttributes: 'fechaInicio', implicitSort: [{ attribute: 'fechaInicio', direction: 'ascending' }]}));

          } else {
            alert("No se puede conectar al servidor...");
          }
        }
        request.ontimeout = function (e) {
          alert("El servicio no se encuentra disponible en este momento... Por favor intenta mas tarde...");
        };
        request.send();
      }
    }

    var vm = new ViewModel();

    Bootstrap.whenDocumentReady().then(
      function () {
        try{
          ko.applyBindings(vm, document.getElementById('tablaWrapper'));
        }catch{}
        ko.applyBindings(vm, document.getElementById('dialogWrapper'));
        ko.applyBindings(vm, document.getElementById('dialogWrapper2'));
        ko.applyBindings(vm, document.getElementById('dialogWrapper3'));
      }
    );
  });
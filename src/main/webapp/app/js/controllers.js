'use strict';

/* Controllers */

function CustomerListCtrl($scope, Customer) {

  $scope.customers = Customer.query();
  $scope.filteredResults = false;
  
  $scope.search = function() {
    $scope.customers = Customer.query(
        {
          searchString: $scope.searchString
        },
        function() {
          $scope.filteredResults = true;
        }
    ); 
  }
  
  $scope.clearSearch = function() {
    $scope.searchString = null;
    $scope.filteredResults = false;
    $scope.customers = Customer.query();
  }
  
  $scope.deleteCustomer = function(customerId) {
    var del = window.confirm('Delete customer1?');
    if (del === true) {
      Customer.delete(
          {
            id: customerId
          },
          function () {
            if (!!$scope.searchString && !!$scope.filteredResults) {
              $scope.search();
            } else {
              $scope.customers = Customer.query();
            }
          });
    }
    }
  
}

function CustomerDetailCtrl($scope, $location, $routeParams, Customer) {

  if ($location.path() === "/new") {
    $scope.customer = {
      createDate : new Date(),
      sex : 'unknown'
    };
  } else {
    $scope.customer = Customer.get({
      id: $routeParams.customerId
    });
  }

  $scope.save = function() {
    if (!!$scope.customer.id) {
      Customer.update($scope.customer, function(customer) {
        $location.path('/list');
      });
    } else {
      Customer.save($scope.customer, function(customer) {
        $location.path('/list');
      });
    }
  }

}

function CompanyListCtrl($scope, Company) {

  $scope.companies = Company.query();
  $scope.filteredResults = false;

  $scope.search = function() {
    $scope.companies = Company.query(
        {
          searchString: $scope.searchString
        },
        function() {
          $scope.filteredResults = true;
        }
    );
  }

  $scope.clearSearch = function() {
    $scope.searchString = null;
    $scope.filteredResults = false;
    $scope.companies = Company.query();
  }

  $scope.deleteCompany = function(companyId) {
      Company.delete(
          {
            id: companyId
          },
          function () {
            if (!!$scope.searchString && !!$scope.filteredResults) {
              $scope.search();
            } else {
              $scope.companies = Company.query();
            }
          });
  }

}
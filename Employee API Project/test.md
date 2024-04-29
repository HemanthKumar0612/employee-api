# Test Cases

### Employee Service

Test case for creating an employee:
* Mock the EmployeeRepository and set up expectations for the save method.
* Call the createEmployee method of EmployeeService with a sample Employee object.
* Assert that the save method of the repository is called with the correct parameters.

Test case for finding all employees:
* Mock the EmployeeRepository and set up expectations for the findAll method.
* Call the findAll method of EmployeeService.
* Assert that the returned list is not null and contains the expected number of employees.

Test case for finding an employee by ID:
* Mock the EmployeeRepository and set up expectations for the findById method.
* Call the findById method of EmployeeService with a sample employee ID.
* Assert that the returned employee matches the expected employee or that the appropriate exception is thrown if the employee is not found.

Test case for finding an employee by last name:
* Mock the EmployeeRepository and set up expectations for the findEmployeeByLastName method. 
* Call the findByLastName method of EmployeeService with a sample last name.
* Assert that the returned employee matches the expected employee or that the appropriate exception is thrown if the employee is not found.

Test case for updating an employee:
* Mock the EmployeeRepository and set up expectations for the findById and save methods.
* Call the updateEmployee method of EmployeeService with a sample employee ID and updated employee details.
* Assert that the findById and save methods are called with the correct parameters.

Test case for deleting an employee:
* Mock the EmployeeRepository and set up expectations for the findById and delete methods.
* Call the deleteEmployee method of EmployeeService with a sample employee ID.
* Assert that the findById and delete methods are called with the correct parameters, and verify that the appropriate message is returned.

# EmployeeRestController Unit Test Cases

## 1. Test create method
- Test with valid employee data.
- Test with invalid employee data (e.g., missing required fields).
- Test for handling exceptions thrown during creation.

## 2. Test getAll method
- Test when there are employees present in the system.
- Test when there are no employees present in the system.

## 3. Test findByLastName method
- Test with an existing last name.
- Test with a non-existing last name.

## 4. Test getById method
- Test with an existing employee ID.
- Test with a non-existing employee ID.

## 5. Test update method
- Test with a valid employee ID and valid employee data for updating.
- Test with an invalid employee ID.
- Test with invalid employee data (e.g., missing required fields).

## 6. Test delete method
- Test with an existing employee ID.
- Test with a non-existing employee ID.

## 7. Test error handling
- Test error handling for invalid input data (e.g., incorrect request body format).
- Test error handling for non-existing resources (e.g., employee not found).
- Test error handling for internal server errors.

## 8. Test response status codes
- Test that the correct HTTP status codes are returned for each operation (e.g., 201 for creation, 404 for not found, etc.).

## 9. Test response body content
- Test that the response body contains the expected data after each operation (e.g., newly created employee details, updated employee details, etc.).

## 10. Test for method security
- Test that only authorized users can access certain endpoints (if applicable).

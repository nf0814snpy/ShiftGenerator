# Shift Management Application

## What will the application do?

This application automatically generates a weekly shift schedule when arranging work shifts by inputting employee's available working hours, positions, and other information. This eliminates the need for manual calculations that can be time-consuming and error-prone. It includes the following features:

- **Creating shift schedules** based on available working hours.
- Distributing **employees in the same position at different times** (e.g., not scheduling Supervisors at the same time, but on different days within the week).
- Equally distributing employees with **varying levels of job experience** (to avoid having all newcomers, for example).
## Who will use it?
Especially, **store manegers** who manage shifts for employees.

## Why is this project of interest to you?

I have a friend who is a nurse living in Japan, and she is responsible for creating shift schedules for nurses at a hospital. She mentioned that the hospital manually creates shift schedules for a large number of nurses, and this process is very time-consuming. As a result, she requested an app for shift scheduling from a service provider, but they declined, stating that creating a shift scheduling app with the specific functionality the hospital desired was challenging.

The desired functionality was to ensure that individuals with less-than-ideal working relationships were not scheduled to work together at the same time. While I found the idea of implementing this feature intriguing, I also thought it would be interesting to expand on this concept. Specifically, I envisioned an application that not only implemented this feature but also divided shifts by job positions and analyzed employee experience to create the most optimal shift schedules.
 
## User Stories
-As a user, I want to be able to **add an employee**.
-As a user, I want to **add the availability** of each employee.
-As a user, I want to **see the details** about employees.
-As a user, I want to **create a shift** by analysing availability.
-As a user, I want to **load the info** about employees(including availability).
-As a user, I want to **save the info** about employee(including availability).

# Instructions for Grader
- To add multiple employees to employee list, you can add from Employee Information by pushing add 
  Employee button and enter informations.
- To add availabilities to employee, you can select employee and click the "See Employee Info", then
  you can click "Add Availability" and enter required infomation.\\
- You can see the details about employees by selecting corresponding employee and clicking "See Employee Information".
- To generate shift, in the generate shift tab, you can click the "Generate" button.
- You can save and load employees infomation from file pull down.
- For the visual component, if you try to delete employee when we don't have any employees,
  this app indicates error with image file.(There is some error image pop up)
  
# Phase 4: Task 2
- Employee added: Someone (ID: 3, Position: Manager, Ideal Work Time: 20.0)
- AvailableDay added for employee Someone Sunday 8.0 - 22.0
  AvailableDay added for employee Someone Monday 15.0 - 22.0
  AvailableDay added for employee Someone Wednesday 8.0 - 22.0
- Availability removed: Someone Monday 15.0 - 22.0
- -Employee removed: Someone (ID: 3, Position: Manager, Ideal Work Time: 20.0)
- Data loaded from ./data/EmployeeInfo.json
- Data saved to ./data/EmployeeInfo.json
- 
# Phase 4: Task 3
- If I had more time, I would combine AvailableDay and Availability. If I combine those classes into one class,
- It would be able to omit so much lines when it comes to code lines. 
- Also, I'd like to organize (maybe combine into one class) ShiftGen and MainMenu class. It is because
  I made shiftGen for phase 1 and MainMenu for phase 3, therefore there is so many similar functions between
- these two classes and I didn't just have a time to delete some functions and combine them.

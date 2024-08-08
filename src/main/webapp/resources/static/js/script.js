var global = {
    APP_NAME: "Aromatherapy_massage",
    RESOURCE_PATH: "/Aromatherapy_massage",
    POST_API_END_POINT: "api/post",
    COURSE_API_END_POINT: "api/course",
    EMPLOYEE_API_END_POINT: "api/employee",
	SERVICE_API_END_POINT: "api/service",
    GET_ACTION: "get",
    HTTP_GET_METHOD: "GET",
    SEPARATOR: "/"
};

// Utility functions
function createXHR() {
    return new XMLHttpRequest();
}

function handleResponse(xhr, callback) {
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
				let result = JSON.parse(xhr.responseText);
				if (result.result == true) {
					callback(result.data);
				} else {
					console.error('API return False result, please check again');
				}
            } else {
                console.error('Error: ' + xhr.statusText);
                displayErrorMessage('Error fetching data. Please try again later.');
            }
        }
    };
}

function sanitizeHtml(unsafeString) {
    const tempDiv = document.createElement('div');
    tempDiv.textContent = unsafeString;
    return tempDiv.innerHTML;
}

function displayErrorMessage(message) {
    const errorContainer = document.getElementById('error-message');
    if (errorContainer) {
        errorContainer.textContent = message;
        errorContainer.style.display = 'block';
    }
}

// Index page functions
function createPostElement(post) {
    let postElement = document.createElement("div");
    postElement.className = "post col";
    postElement.innerHTML = `
        <div class="img-container">
            <img src="${global.RESOURCE_PATH+post.postExcerptImgSrc}" alt="${post.postTitle}" class="img-fluid">
        </div>
        <h4 class="post-title">${sanitizeHtml(post.postTitle)}</h4>
        <p class="post-excerpt">${sanitizeHtml(post.postExcerpt)}</p>`;
    return postElement;
}

function populatePostList(list) {
    let container = document.querySelector(`#post-list`);
    const fragment = document.createDocumentFragment();
    list.forEach((post, index) => {
        if (index < 3) { // Display only the first 3 posts
            fragment.appendChild(createPostElement(post));
        }
    });
    container.appendChild(fragment);
}

function fetchPostList() {
    let xhr = createXHR();
    xhr.open(global.HTTP_GET_METHOD, `${global.POST_API_END_POINT}${global.SEPARATOR}${global.GET_ACTION}`, true);
    handleResponse(xhr, populatePostList);
    xhr.send();
}

function createCourseElement(course) {
    let courseElement = document.createElement("div");
    courseElement.className = "course row";
    let courseCreateDate = new Date(course.courseCreateDate);
    let date = courseCreateDate.toLocaleDateString("en-US", { year: 'numeric', month: 'short', day: '2-digit' });
    courseElement.innerHTML = `
        <div class="col-md-3">
            <div class="course-date">${sanitizeHtml(date)}</div>
        </div>
        <div class="col-md-9">
            <h4 class="course-title">${sanitizeHtml(course.courseTitle)}</h4>
            <p class="course-excerpt">${sanitizeHtml(course.courseContent)}</p>
        </div>`;
    return courseElement;
}

function populateCourseList(resultList) {
    let container = document.querySelector("#course-list");
    const fragment = document.createDocumentFragment();
    resultList.forEach((course, index) => {
        fragment.appendChild(createCourseElement(course));
    });
    container.appendChild(fragment);
}

function fetchCourseList() {
    let xhr = createXHR();
    xhr.open(global.HTTP_GET_METHOD, `${global.COURSE_API_END_POINT}${global.SEPARATOR}${global.GET_ACTION}`, true);
    handleResponse(xhr, populateCourseList);
    xhr.send();
}

function fetchAppointmentEmployee() {
    let xhr = createXHR();
    let queryString = `${global.GET_ACTION}?id=3`;
    xhr.open(global.HTTP_GET_METHOD, `${global.EMPLOYEE_API_END_POINT}${global.SEPARATOR}${queryString}`, true);
    handleResponse(xhr, (result) => {
        let employee = result.model;
        document.querySelector(".avt-img img").src = global.RESOURCE_PATH + employee.employeeImgSrc;
        document.querySelector("#signature").innerHTML = sanitizeHtml(employee.employeeName);
    });
    xhr.send();
}

// Therapy and Massage page functions
function createTherapyMassagePostElement(post) {
    let postElement = document.createElement("div");
    postElement.className = "post mb-4";
    postElement.innerHTML = `
        <div class="row">
			<h3 class="post-title">${sanitizeHtml(post.postTitle)}</h3>
            <div class="col-md-6">
                <img src="${sanitizeHtml(global.RESOURCE_PATH+post.postExcerptImgSrc)}" alt="${sanitizeHtml(post.postTitle)}" class="img-fluid">
            </div>
            <div class="col-md-6">
                
                <p class="post-excerpt">${sanitizeHtml(post.postContent.slice(0,297) + "...")}</p>
				<a href="#"> <i><u>Read more</u></i></a>
            </div>
        </div>`;
    return postElement;
}


function createTherapyMassagePostElementReverse(post) {
    let postElement = document.createElement("div");
    postElement.className = "post mb-4";
    postElement.innerHTML = `
        <div class="row">
			<h3 class="post-title">${sanitizeHtml(post.postTitle)}</h3>
            <div class="col-md-6">
                <p class="post-excerpt">${sanitizeHtml(post.postContent.slice(0,297) + "...")}</p>
				<a href="#"> <i><u>Read more</u></i></a>
            </div>
			<div class="col-md-6">
                <img src="${sanitizeHtml(global.RESOURCE_PATH+post.postExcerptImgSrc)}" alt="${sanitizeHtml(post.postTitle)}" class="img-fluid">
            </div>
        </div>`;
    return postElement;
}

function populateTherapyMassagePosts(posts) {
    let container = document.querySelector("#therapy-massage-posts");
    const fragment = document.createDocumentFragment();
    posts.forEach((post, index) => {
		if (index % 2 == 0) {
        	fragment.appendChild(createTherapyMassagePostElement(post));
		} else {
			fragment.appendChild(createTherapyMassagePostElementReverse(post))
		}
    });
    container.appendChild(fragment);
}


function fetchTherapyMassagePosts() {
    let xhr = createXHR();
    xhr.open(global.HTTP_GET_METHOD, `${global.POST_API_END_POINT}${global.SEPARATOR}${global.GET_ACTION}`, true);
    handleResponse(xhr, (posts) => populateTherapyMassagePosts(posts));
    xhr.send();
}

function createServicePriceListRow(service) {
    let serviceElement = document.createElement("div");
    serviceElement.className = "row g-0 px-2";
    serviceElement.innerHTML = `
			<div class="service-title col-md-5">${sanitizeHtml(service.serviceTitle)}</div>
            <div class="service-excerpt col-md-6">${sanitizeHtml(service.serviceInfo)}</div>
            <div class="service-price col-md-1">$${sanitizeHtml(service.servicePrice)}</div>
			`;
    return serviceElement;
}

function populateServicePriceLists(services) {
    let container = document.querySelector("#service-price-lists");
    const fragment = document.createDocumentFragment();
    services.forEach(service => {
    	fragment.appendChild(createServicePriceListRow(service));
    });
    container.appendChild(fragment);
}

function fetchServicePriceLists() {
    let xhr = createXHR();
    xhr.open(global.HTTP_GET_METHOD, `${global.SERVICE_API_END_POINT}${global.SEPARATOR}${global.GET_ACTION}`, true);
    handleResponse(xhr, (services) => populateServicePriceLists(services)); // Display only the latest 3 posts
    xhr.send();
}

function createEmployeeElement(employee) {
    let employeeElement = document.createElement("row");
    employeeElement.className = "employee";
    employeeElement.innerHTML = `
        <div class="row">
			<h3 class="employee-name">${sanitizeHtml(employee.employeeName)}</h3>
            <div class="col-md-3">
				<img src="${sanitizeHtml(global.RESOURCE_PATH+employee.employeeImgSrc)}" alt="${sanitizeHtml(employee.employeeName)}" class="img-fluid">
            </div>
			<div class="col-md-9">
				<h3 class="employee-title">${sanitizeHtml(employee.employeeTitle)}</h3>
				<p class="employee-info">${sanitizeHtml(employee.employeeInfo)}</p>
                
            </div>
        </div>`;
    return employeeElement;
}


function populateEmployeeLists(employees) {
    let container = document.querySelector("#employee-lists");
    const fragment = document.createDocumentFragment();
    employees.forEach(employee => {
    	fragment.appendChild(createEmployeeElement(employee));
    });
    container.appendChild(fragment);
}

function fetchEmployeeLists() {
    let xhr = createXHR();
    xhr.open(global.HTTP_GET_METHOD, `${global.EMPLOYEE_API_END_POINT}${global.SEPARATOR}${global.GET_ACTION}`, true);
    handleResponse(xhr, (employees) => populateEmployeeLists(employees)); // Display only the latest 3 posts
    xhr.send();
}

// Main initialization
function initializePage() {
    const currentPage = document.body.dataset.page;

    switch (currentPage) {
        case "index":
            fetchPostList();
            fetchCourseList();
            fetchAppointmentEmployee();
            break;
        case "therapyMassage":
            fetchTherapyMassagePosts();
            break;
		case "priceList":
			fetchServicePriceLists()
			break;
		case "employeeList":
			fetchEmployeeLists()
        // Add more cases as needed for different pages
        default:
            console.log("No starter functions for this page");
    }
	
	//add event listener
	const topMenu = document.getElementById("top-menu");

    topMenu.addEventListener("click", function(event) {
        // Ensure the clicked element is an <a> tag
        if (event.target.matches("#top-menu > a")) {
            // Remove "active" class from all links
            topMenu.querySelectorAll("a.active").forEach(link => link.classList.remove("active"));

            // Add "active" class to the clicked link
            event.target.classList.add("active");
        }
    });
}

document.addEventListener("DOMContentLoaded", initializePage);
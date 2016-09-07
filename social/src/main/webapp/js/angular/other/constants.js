'use strict';
app.constant('CONTROLLER', (function() {
	return {
		CTRL: 'ctrl',
		POST_CONTROLLER: 'PostController',
		TOPIC_CONTROLLER: 'TopicController',
		TOPIC_EDIT_CONTROLLER: 'TopicEditController',
		USER_EDIT_CONTROLLER: 'UserEditController'
	}
})());

app.constant('MESSAGE', (function() {
	var creatingError = 'Error while creating ';
	var updatingError = 'Error while updating ';
	var gettingError = 'Error while getting ';
	return {
		AUTHENTICATION_ERROR: 'Login or password is wrong',
		TAKEN_LOGIN_ERROR: 'This login is already taken',
		TAKEN_TOPIC_NAME_ERROR: 'This name is already taken',
		TAKEN_TOPIC_URL_NAME_ERROR: 'This url name is already taken',
		SAVING_FILE_ERROR: 'Error while saving file',
		CREATING_POST_ERROR: creatingError + 'post',
		CREATING_TOPIC_ERROR: creatingError + 'topic',
		CREATING_USER_ERROR: creatingError + 'user',
		UPDATING_POST_ERROR: updatingError + 'post',
		UPDATING_TOPIC_ERROR: updatingError + 'topic',
		UPDATING_USER_ERROR: updatingError + 'user',
		GETTING_POST_ERROR: gettingError + 'post',
		GETTING_TOPIC_ERROR: gettingError + 'topic',
		GETTING_USER_ERROR: gettingError + 'user'
	}
})());

app.constant('PATH', (function() {
	var path = 'social/html';
	var contentPath = path + '/content';
	var formPath = path + '/form';
	var infoPath = path + '/info';
	var titlePath = path + '/title';
	var toolPath = path + '/tool';
	var htmlExt = '.html';
	return {
		POST_CONTENT: contentPath + '/post' + htmlExt,
		TOPIC_CONTENT: contentPath + '/topic' + htmlExt,
		LOGIN_FORM: formPath + '/login' + htmlExt,
		REGISTER_FORM: formPath + '/register' + htmlExt,
		TOPIC_EDIT_FORM: formPath + '/topic.edit' + htmlExt,
		TOPIC_INFO: infoPath + '/topic.info' + htmlExt,
		TOPICS_INFO: infoPath + '/topics.info' + htmlExt,
		FOOTER: titlePath + '/footer' + htmlExt,
		HEADER: titlePath + '/header' + htmlExt,
		WELCOME_HEADER: titlePath + '/welcome.header' + htmlExt,
		PAGINATION_TOOL: toolPath + '/pagination' + htmlExt,
		SEARCH_TOOL: toolPath + '/search' + htmlExt
	}
})());

app.constant('REST', (function() {
	var url = '/social';
	return {
		JSON_EXT: '.json',
		POSTS: url + '/posts',
		TOPICS: url + '/topics',
		USERS: url + '/users'
	}
})());

app.constant('STATE', (function() {
	var topic = 'topic';
	var topics = 'topics';
	var addOperation = '_add';
	return {
		LOGIN: 'login',
		REGISTER: 'register',
		SEARCH: 'search',
		TOPIC_ADD: topic + addOperation,
		FEED: 'feed',
		TOPIC: topic,
		TOPICS: topics
	}
})());

app.constant('TITLE', (function() {
	return {
		LOGIN: 'Login',
		REGISTER: 'Register',
		SEARCH: 'Search',
		FEED: 'Feed',
		TOPICS: 'Topics'
	}
})());

app.constant('URL', (function() {
	var url = '/social';
	var feedUrl = '/feed';
	var topicUrl = '/topic';
	var topicsUrl = '/topics';
	var addOperation = '/add';
	var pageOperation = '/page';
	var idPattern = '/{id:[0-9]{1,}}';
	var pagePattern = '/{page:[0-9]{1,}}';
	var pathPattern = '/{path:[a-z0-9._]{1,}}';
	return {
		LOGIN: url + '/login',
		REGISTER: url + '/register',
		SEARCH: url + '/search',
		TOPIC_ADD: url + topicUrl + addOperation,
		FEED: url + feedUrl + pageOperation + pagePattern,
		TOPIC: url + topicUrl + pathPattern + pageOperation + pagePattern,
		TOPICS: url + topicsUrl + pageOperation + pagePattern
	}
})());
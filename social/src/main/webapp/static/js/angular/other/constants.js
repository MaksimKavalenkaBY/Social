'use strict';
app.constant('CONTROLLER', (function() {
	return {
		CTRL: 'ctrl',
		INFO_CONTROLLER: 'InfoController',
		NOTIFICATION_CONTROLLER: 'NotificationController',
		PAGINATION_CONTROLLER: 'PaginationController',
		POST_CONTROLLER: 'PostController',
		TOPIC_CONTROLLER: 'TopicController',
		USER_CONTROLLER: 'UserController'
	}
})());

app.constant('MESSAGE', (function() {
	var deletingError = 'Error while deleting ';
	var gettingError = 'Error while getting ';
	return {
		AUTHENTICATION_ERROR: 'Login or password is wrong',
		DELETING_NOTIFICATION_ERROR: deletingError + 'notification',
		DELETING_POST_ERROR: deletingError + 'post',
		GETTING_NOTIFICATION_ERROR: gettingError + 'notification',
		GETTING_POST_ERROR: gettingError + 'post',
		GETTING_TOPIC_ERROR: gettingError + 'topic',
		GETTING_USER_ERROR: gettingError + 'user',
		PASSWORDS_ERROR: 'Passwords do not match',
		SAVING_FILE_ERROR: 'Error while saving file',
		TAKEN_LOGIN_ERROR: 'This login is already taken',
		TAKEN_PATH_ERROR: 'This path is already taken',
		VALIDATION_ERROR: 'All required attributes must be filled'
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
		NOTIFICATION_CONTENT: contentPath + '/notification' + htmlExt,
		POST_CONTENT: contentPath + '/post' + htmlExt,
		TOPIC_CONTENT: contentPath + '/topic' + htmlExt,
		INVITE_FORM: formPath + '/invite' + htmlExt,
		LOGIN_FORM: formPath + '/login' + htmlExt,
		PROFILE_FORM: formPath + '/profile' + htmlExt,
		PROFILE_PHOTO_FORM: formPath + '/profile.photo' + htmlExt,
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
		NOTIFICATIONS: url + '/notifications',
		POSTS: url + '/posts',
		TOPICS: url + '/topics',
		UPLOAD: url + '/upload',
		USERS: url + '/users'
	}
})());

app.constant('STATE', (function() {
	var profile = 'profile';
	var topic = 'topic';
	return {
		LOGIN: 'login',
		REGISTER: 'register',
		PROFILE: profile,
		PROFILE_PHOTO: profile + '_photo',
		TOPIC_ADD: topic + '_add',
		TOPIC_EDIT: topic + '_edit',
		FEED: 'feed',
		TOPIC: topic,
		TOPICS: 'topics',
		POST: 'post',
		NOTIFICATIONS: 'notifications',
		SEARCH: 'search'
	}
})());

app.constant('TITLE', (function() {
	return {
		LOGIN: 'Login',
		REGISTER: 'Register',
		PROFILE: 'Profile',
		TOPIC_ADD: 'Create topic',
		POST: 'Post',
		FEED: 'Feed',
		TOPICS: 'Topics',
		NOTIFICATIONS: 'Notifications',
		SEARCH: 'Search'
	}
})());

app.constant('URL', (function() {
	var url = '/social';
	var loginUrl = url + '/login';
	var registerUrl = url + '/register';
	var profileUrl = url + '/profile';
	var profilePhotoUrl = profileUrl + '/photo';
	var feedUrl = url + '/feed';
	var notificationsUrl = url + '/notifications';
	var searchUrl = url + '/search';
	var topicUrl = url + '/topic';
	var topicsUrl = url + '/topics';
	var postUrl = url + '/post';
	var addOperation = '/add';
	var editOperation = '/edit';
	var idKey = '{id:[0-9]{1,}}';
	var pageKey = '{page:[0-9]{1,}}';
	var pathKey = '{path:[a-z0-9_]{1,}}';
	var valueKey = '{value}';
	return {
		HOME: feedUrl + '?page=1',
		LOGIN: loginUrl,
		REGISTER: registerUrl,
		PROFILE: profileUrl,
		PROFILE_PHOTO: profilePhotoUrl,
		TOPIC_ADD: topicUrl + addOperation,
		TOPIC_EDIT: topicUrl + '/' + pathKey + editOperation,
		FEED: feedUrl + '?' + pageKey,
		TOPIC: topicUrl + '/' + pathKey + '?' + pageKey,
		TOPICS: topicsUrl + '?' + pageKey,
		POST: postUrl + '/' + pathKey + '?' + idKey,
		NOTIFICATIONS: notificationsUrl + '?' + pageKey,
		SEARCH: searchUrl + '?' + valueKey + '&' + pageKey
	}
})());
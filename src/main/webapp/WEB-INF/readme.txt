mongoDB:
database: PhotoUploaderDB
collections: Users, Photos

Users: {
    _id
    firstName
    lastName
    email
    password
    role
    login
    photos{
        _id
        uploadDate
        photo
        comments
    }
}


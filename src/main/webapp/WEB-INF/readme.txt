mongoDB:
database: PhotoUploaderDB
collections: Users, Photos

Users: {
    id
    firstName
    lastName
    email
    password
    role
    login
    photos{
        id
        uploadDate
        photo
        comments
    }
}


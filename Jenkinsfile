pipeline
{
    agent
    { 
        docker
        { 
            image 'maven:3.6.3-adoptopenjdk-8'
        }
    }
    stages
    {
        stage('clean')
        {
            steps
            {
                sh 'mvn clean'
            }
        }
        stage('build')
        {
            steps
            {
                sh 'mvn package'
            }
        }
        stage('test')
        {
            steps
            {
                sh 'mvn test'
            }
        }
    }
}
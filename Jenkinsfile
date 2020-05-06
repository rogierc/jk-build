pipeline
{
    agent
    { 
        docker
        { 
            image 'maven:3.3.3'
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
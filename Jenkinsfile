pipeline
{
    agent
    { 
        dockerfile true 
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
        stage('deploy')
        {
            steps
            {
                sh 'mvn deploy'
            }
        }
    }
}
pipeline
{
    agent
    { 
        dockerfile
        {
            args '-v pipine_build_env:/build_env'
        } 
    }
    stages
    {
        stage('prepare')
        {
         
            steps
            {
                sh 'echo $MAVEN_OPTS'
            }
        }
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
            environment
            {
                GNUPGHOME = '/build_env/.gnupg'
            }   
            steps
            {
                sh 'mvn deploy'
            }
        }
    }
}
pipeline
{
    agent
    { 
        dockerfile true
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
                GNUPGHOME = '/var/jenkins_home/.gnupg/'
            }   
            steps
            {
                withMaven( mavenSettingsConfig: 'teslanet-maven-settings.xml' )
                 {
                    sh 'mvn deploy'
                }
            }
        }
    }
}
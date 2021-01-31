pipeline
{
    environment
    {
        MVN_SETTINGS = credentials( 'secret-teslanet-maven-settings.xml' )
    }
    agent
    { 
        dockerfile true
        args '--network sonar_network'
    }
    stages
    {
        stage('build')
        {
            steps
            {
                sh 'mvn -B clean package'
            }
        }
        stage('test')
        {
            steps
            {
                sh 'mvn -B test'
            }
        }
        stage('install')
        {
            environment
            {
                GNUPGHOME = '/var/jenkins_home/.gnupg/'
            }   
            steps
            {
               sh 'mvn -B -s $MVN_SETTINGS install'
            }
        }
        stage('scan')
        {
            environment
            {
                GNUPGHOME = '/var/jenkins_home/.gnupg/'
            }   
            steps
            {
               sh 'mvn -B -s $MVN_SETTINGS sonar:sonar -Psonar -Pcoverage'
            }
        }
    }
}
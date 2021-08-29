pipeline
{
    parameters
    {
        booleanParam (  name: 'DO_DEPLOY_SNAPSHOT',  defaultValue: false, description: 'when true snapshot is deployed' )
        booleanParam (  name: 'DO_RELEASE',  defaultValue: false, description: 'when true  a release is built' )
    }
    environment
    {
        MVN_SETTINGS = credentials( 'secret-teslanet-maven-settings.xml' )
        GNUPGHOME = '/var/jenkins_home/.gnupg/'
    }
    agent
    { 
        dockerfile
        {
            args '--network sonar_network'
        }
    }
    stages
    {
        stage('prepare')
        {
            steps
            {
               sh '''
                git config user.email "jenkins@teslanet.nl"
                git config user.name "jenkins"
                mvn -B -s $MVN_SETTINGS release:clean
                '''
            }
        }
        stage('build')
        {
            steps
            {
                sh 'mvn -B -s $MVN_SETTINGS clean package -DskipTest'
            }
        }
        stage('verify')
        {
            when
            {
                not
                { 
                	expression { params.DO_DEPLOY_SNAPSHOT }
                }
            }
            steps
            {
                sh 'mvn -B -s $MVN_SETTINGS verify sonar:sonar -Psonar'
            }
        }
        stage('verify and deploy')
        {
            when
            {
                expression { params.DO_DEPLOY_SNAPSHOT }
            }
             steps
            {
                sh 'mvn -B -s $MVN_SETTINGS deploy sonar:sonar -Psonar'
            }
        }
        stage('release-prepare')
        {
            when
            {
                expression { params.DO_RELEASE }
            }
            steps
            {
               sh 'mvn -B -s $MVN_SETTINGS release:prepare'
            }
        }
        stage('release-perform')
        {
            when
            {
                expression { params.DO_RELEASE }
            }
            steps
            {
               sh 'mvn -B -s $MVN_SETTINGS release:perform'
            }
        }
    }
}
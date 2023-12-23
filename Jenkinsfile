pipeline
{
    parameters
    {
        booleanParam (  name: 'DEPLOY_SNAPSHOT',  defaultValue: false, description: 'when true snapshot is deployed' )
        booleanParam (  name: 'BUILD_RELEASE',  defaultValue: false, description: 'when true  a release is built' )
    }
    environment
    {
        MVN_SETTINGS = credentials( 'secret-teslanet-maven-settings.xml' )
        GNUPGHOME = '/var/lib/jenkins_keys/.gnupg'
    }
    agent
    { 
        dockerfile
        {
            filename 'AgentDockerfile'
            args '--network sonar_network --volume jenkins_keys:/var/lib/jenkins_keys --volume "jenkinsagent_m2repo:/home/jenkins/.m2/repository' }
    }
    options
    { 
        buildDiscarder( logRotator(numToKeepStr: '30' ))
    }
    stages
    {
        stage('prepare')
        {
            when
            {
                expression { params.BUILD_RELEASE }
            }
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
                sh 'mvn -B -s $MVN_SETTINGS clean package -DskipTests'
            }
        }
        stage('verify')
        {
            when
            {
                not
                { 
                	expression { params.DEPLOY_SNAPSHOT }
                }
            }
            steps
            {
                sh 'mvn help:evaluate -Dexpression=sonar.projectKey'
                sh '''
                	mvn sonar:sonar \
  					-Dsonar.projectKey=jk-build \
  					-Dsonar.scm.disabled=true \
  					-Dsonar.host.url=http://sonar:9000 \
  					-Dsonar.login=d22fb96c69714a954d58ed2b865324543e9f3a1e
  				'''
                sh 'mvn -B -s $MVN_SETTINGS verify sonar:sonar -Psonar -Dsonar.projectKey=jk-build'
            }
        }
        stage('verify and deploy')
        {
            when
            {
                expression { params.DEPLOY_SNAPSHOT }
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
                expression { params.BUILD_RELEASE }
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
                expression { params.BUILD_RELEASE }
            }
            steps
            {
               sh 'mvn -B -s $MVN_SETTINGS release:perform'
            }
        }
    }
    post
    {
        // Clean after build
        always
        {
            cleanWs( cleanWhenNotBuilt: false,
                     deleteDirs: true,
                     disableDeferredWipeout: true,
                     notFailBuild: true
                   )
        }
    }
}
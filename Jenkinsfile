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
                sh 'curl -v -u b16790e40cd9c43caa39472fe850704df8ccc07c: http://sonar:9000/api/project_links/search?projectKey=test'
                sh '''
                mvn sonar:sonar \
  					-Dsonar.projectKey=test \
  					-Dsonar.host.url=http://192.168.0.20:9000 \
  					-Dsonar.login=7017dba0e13c0a317d98335884a32c875a782c3a
  				'''
                sh 'mvn -B -s $MVN_SETTINGS verify sonar:sonar -Psonar'
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
#version 120

uniform sampler2D texture;
uniform float radius;
uniform vec2 texelSize;

varying vec2 uv;

void main() {
    vec2 texCoordCentered = uv * texelSize - texelSize/2.0;
    float dist = length(texCoordCentered);
    float mask = smoothstep(radius, radius + 0.1, dist);
    vec4 smpl = texture2D(texture, uv);
    gl_FragColor = vec4(smpl) * mask;
}